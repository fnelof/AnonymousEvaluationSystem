package gr.unipi.evaluate.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import gr.unipi.evaluate.common.Constants;
import gr.unipi.evaluate.service.CourseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import gr.unipi.evaluate.service.EvaluationService;

@RestController
public class EvaluationController {

	private static final Logger logger = LogManager.getLogger(EvaluationController.class);

	@Autowired
	EvaluationService evaluationService;

	@Autowired
	CourseService courseService;
	
	// Handles the evaluation request
	@PostMapping(value = "/vote")
	public String vote(@RequestBody MultiValueMap<String, String> formData) {
		logger.info("Start vote for instructorId: {} - courseId: {}", formData.get("instructorId"), formData.get("courseId"));
		try {
			BigInteger courseId = new BigInteger(Objects.requireNonNull(formData.getFirst("courseId")));
			BigInteger instructorId = new BigInteger(Objects.requireNonNull(formData.getFirst("instructorId")));
			List<BigInteger> ticketChain = fixTicketChain(new ArrayList(formData.get("ticketChain[]")));
			String initialTicket = formData.getFirst("initialTicket");
			String eval = formData.getFirst("eval");
			String comment = formData.getFirst("comment");
			String EATHash = formData.getFirst("EAT");
			BigInteger EAT = new BigInteger(EATHash,16);
			JSONObject response = new JSONObject();
			if(courseService.isEligible(ticketChain, initialTicket, courseId,instructorId)){
				response = evaluationService.submitTicketAndEvaluation(courseId, instructorId, initialTicket, EAT, eval, comment);
			}
			logger.info("End vote for instructorId: {} - courseId: {}", instructorId, courseId);
			return response.toString();//response.toString();

		// Handles unknown exceptions and hides them from the user
		}catch(Exception ex) {
			logger.error("Unknown exception occurred in vote: {}", ex);
			JSONObject response = new JSONObject();
			response.put(Constants.ERROR_RESPONSE_OBJECT, "Unknown error");
			return response.toString();
		}
	}

	private List<BigInteger> fixTicketChain(ArrayList arrayList) {
		List<BigInteger> result = new ArrayList<>();
		for(Object o: arrayList){
			BigInteger temp = new BigInteger(o.toString());
			result.add(temp);
		}
		return result;
	}
}
