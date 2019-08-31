package gr.unipi.evaluate.service;

import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

import gr.unipi.evaluate.common.Constants;
import org.hibernate.HibernateException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.mariadb.jdbc.internal.common.QueryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gr.unipi.evaluate.dao.EvaluationDao;
import gr.unipi.evaluate.model.Course;
import gr.unipi.evaluate.model.Evaluation;
import gr.unipi.evaluate.model.Instructor;
import gr.unipi.evaluate.model.Ticket;

@Service
public class EvaluationServiceImp implements EvaluationService{
	@Autowired
	TicketService ticketService;
	
	@Autowired
	EvaluationDao evaluationDao;
	
	/* 
	 * Submits the ticket and the evaluation of the instructor 
	 * after all the necessary prerequisites are checked
	*/
	@Transactional
	public JSONObject submitTicketAndEvaluation(BigInteger courseId,BigInteger instructorId,
			String message, BigInteger signedTicket,
			String eval,String comment) {
		JSONObject response = new JSONObject();
		try {

			// check if ticket is valid ( verification(signedTicket) = h(m) )
			if(ticketService.isValid(message, signedTicket)) {
			
				String ticket = ticketService.generateHash(message);
				
				// check if ticket is already used 
				if(ticketService.isUsed(ticket)) {
					
					response.put("error","The ticket has been used already");
					return response;
				}
				
				JSONObject e = new JSONObject(eval);
				JSONArray jsonArray = e.getJSONArray(Constants.VOTE_LIST);
				
				List<Evaluation> evaluationList = new ArrayList<>();
				
				// Populate evaluationList
				for(int i=0; i<jsonArray.length();i++) {
					JSONObject question =  jsonArray.getJSONObject(i);
					int questionId = question.getInt(Constants.ID);
					int vote= question.getInt(Constants.VOTE);
									
					Evaluation evaluation = new Evaluation(ticket,courseId,instructorId,questionId,vote);
					evaluationList.add(evaluation);
				}
				Ticket t = new Ticket(ticket,new Course(courseId),new Instructor(instructorId),comment);
				
				// submit ticket and comment
				ticketService.submitTicket(t);
				
				// Submit all evaluations
				for(Evaluation evaluation: evaluationList) {
					evaluationDao.submitEvaluation(evaluation);
				}				
				response.put("success", "Your evaluation was submitted successfully!");
				return response;
			}
			
			response.put("error", "Message and signed ticket are not a valid combination");
			
			return response;
			// Handles possible back end exceptions and notifies the user about it
		} catch (NoSuchAlgorithmException | FileNotFoundException | CertificateException | JSONException  e ) {
			e.printStackTrace();
			
			response.put("error", "Something went wrong on our end. Please contact an administrator for further details");
			return response;
		// Handles possible database exceptions and notifies the user accordingly
		} catch(HibernateException | IllegalArgumentException 
				| DataIntegrityViolationException | QueryException e) {
			response.put("error","Something went wrong with processing your evaluation form. Refresh the page and try again. If the error still occurs contact one of our administrators.");
			return response;
			
		// Handles all other exceptions that could occur
		} catch(Exception e) {
			response.put("error", "Unknown error");
			e.printStackTrace();
			return response;
		}		
	}
}
