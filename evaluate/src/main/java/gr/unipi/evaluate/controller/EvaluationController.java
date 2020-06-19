package gr.unipi.evaluate.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import gr.unipi.evaluate.common.Constants;
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

	
	// Handles the evaluation request
	@PostMapping(value = "/vote")
	public String vote(@RequestBody MultiValueMap<String, String> formData) {
		logger.info("Start vote for instructorId: {} - courseId: {}", formData.get("instructorId"), formData.get("courseId"));
		try {
			BigInteger courseId = new BigInteger(formData.get("courseId").get(0).toString());
			BigInteger instructorId = new BigInteger(formData.get("instructorId").get(0).toString());
			List ticketChain = new ArrayList(formData.get("ticketChain[]"));
			String initialTicket = formData.getFirst("initialTicket");
			BigInteger EAT = new BigInteger(formData.getFirst("EAT"));


			//JSONObject response = evaluationService.submitTicketAndEvaluation(courseId, instructorId, initialTicket, EAT, eval, comment);
			logger.info("End vote for instructorId: {} - courseId: {}", instructorId, courseId);
			return "";//response.toString();

		// Handles unknown exceptions and hides them from the user
		}catch(Exception ex) {
			logger.error("Unknown exception occurred in vote: {}", ex);
			JSONObject response = new JSONObject();
			response.put(Constants.ERROR_RESPONSE_OBJECT, "Unknown error");
			return response.toString();
		}
	}
}
