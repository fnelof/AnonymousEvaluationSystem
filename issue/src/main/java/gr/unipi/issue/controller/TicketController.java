package gr.unipi.issue.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gr.unipi.issue.service.PrivateKeyDetailsService;

@RestController
public class TicketController {
	@Autowired
	PrivateKeyDetailsService privateKeyService;
	
	// Issues the ticket (signature of the blinded ticket)
	@RequestMapping(value="issueTicket",method = RequestMethod.POST)
	public String getTicket(@RequestParam BigInteger blindedTicket,
			@RequestParam BigInteger courseId,
			@RequestParam BigInteger instructorId) {
				
		BigInteger signedBlindedTicket;
		
		JSONObject response = new JSONObject();

		try {
			signedBlindedTicket = privateKeyService.signMessage(blindedTicket);
						
			response.put("signedBlindedTicket", signedBlindedTicket.toString());
			return response.toString();
			// Handles and informs user about a possible exception
		} catch (UnrecoverableKeyException | KeyStoreException | NoSuchAlgorithmException | CertificateException
				| IOException e) {

			e.printStackTrace();
			response.put("error", "Something went wrong on our end. Please contact the administrator for further details");
			
			return response.toString();
		}
	}
}
