package gr.unipi.evaluate.service;

import java.math.BigInteger;

import org.json.JSONObject;



public interface EvaluationService {
	public JSONObject submitTicketAndEvaluation(BigInteger courseId,BigInteger instructorId, String message, BigInteger signedTicket,
			String eval,String comment);

}
