package gr.unipi.evaluate.controller;

import java.math.BigInteger;

import gr.unipi.evaluate.common.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import gr.unipi.evaluate.service.EvaluationService;

@RestController
public class EvaluationController {

	private static final Logger logger = LogManager.getLogger(EvaluationController.class);

	@Autowired
	EvaluationService evaluationService;

	
	// Handles the evaluation request 
	@PostMapping(value = "/vote")
	public String vote(@RequestParam BigInteger courseId,
			@RequestParam BigInteger instructorId,
			@RequestParam String message,
			@RequestParam BigInteger signedTicket,
			@RequestParam String eval,
			@RequestParam String comment
			) {
		logger.info("Start vote for instructorId: {} - courseId: {}", instructorId, courseId);
		try {
			JSONObject response = evaluationService.submitTicketAndEvaluation(courseId, instructorId, message, signedTicket, eval, comment);
			logger.info("End vote for instructorId: {} - courseId: {}", instructorId, courseId);
			return response.toString();

		// Handles unknown exceptions and hides them from the user
		}catch(Exception ex) {
			logger.error("Unknown exception occurred in vote: {}", ex);
			JSONObject response = new JSONObject();
			response.put(Constants.ERROR_RESPONSE_OBJECT, "Unknown error");
			return response.toString();
		}
	}
}
