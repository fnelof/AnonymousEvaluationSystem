package gr.unipi.issue.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

import gr.unipi.issue.common.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import gr.unipi.issue.service.PrivateKeyDetailsService;

@RestController
public class TicketController {

	private static final Logger logger = LogManager.getLogger(TicketController.class);

	@Autowired
	PrivateKeyDetailsService privateKeyService;
	
	// Issues the ticket (signature of the blinded ticket)
	@PostMapping(value="issueTicket")
	public String getTicket(@RequestParam BigInteger blindedTicket,
			@RequestParam BigInteger courseId,
			@RequestParam BigInteger instructorId) {
		logger.info("Start getTicket");
		BigInteger signedBlindedTicket;
		
		JSONObject response = new JSONObject();

		try {

			signedBlindedTicket = privateKeyService.signMessage(blindedTicket, courseId);

			response.put(Constants.BLIND_SIGNATURE, signedBlindedTicket.toString());
			logger.info("End getTicket");
			return response.toString();
			// Handles and informs user about a possible exception
		} catch(NoSuchAlgorithmException ex){
			logger.error("No such algorithm exception in getTicket", ex);

		}catch (IOException ex) {

			logger.error("There was something wrong with the certificate in getTicket: ", ex);
		}catch(Exception ex){
			logger.error("Unknown exception thrown in getTicket: ", ex);
		}

		response.put(Constants.ERROR_RESPONSE_OBJECT, "Something went wrong on our end. Please contact the administrator for further details");
		return response.toString();
	}
}
