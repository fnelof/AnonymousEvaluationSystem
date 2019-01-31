package gr.unipi.evaluate.controller;

import java.math.BigInteger;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gr.unipi.evaluate.service.EvaluationService;

@RestController
public class EvaluationController {
	@Autowired
	EvaluationService evaluationService;

	
	// Handles the evaluation request 
	@RequestMapping(value = "/vote", method= RequestMethod.POST)
	public String vote(@RequestParam BigInteger courseId,
			@RequestParam BigInteger instructorId,
			@RequestParam String message,
			@RequestParam BigInteger signedTicket,
			@RequestParam String eval,
			@RequestParam String comment
			) {
		try {
			JSONObject response = evaluationService.submitTicketAndEvaluation(courseId, instructorId, message, signedTicket, eval, comment);
			return response.toString();

		// Handles unknown exceptions and hides them from the user
		}catch(Exception ex) {
			JSONObject response = new JSONObject();
			response.put("error", "Unknown error");
			return response.toString();
		}
	}
}
