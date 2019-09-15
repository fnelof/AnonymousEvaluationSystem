package gr.unipi.issue.controller;

import java.io.IOException;

import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import gr.unipi.issue.common.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.unipi.issue.model.PublicKeyDetails;
import gr.unipi.issue.service.PublicKeyService;

@RestController
public class PublicKeyController {

	private static final Logger logger = LogManager.getLogger(PublicKeyController.class);


	@Autowired
	PublicKeyService publicKeyService;
	
	/*
	 * Handles the request for the public key details
	 * The public key is needed to encrypt and blind the ticket 
	*/
	@GetMapping("/sendPublicKey")
	public String sendPublicKey() {
		logger.info("Start sendPublicKey");
		JSONObject response =  new JSONObject();
		
		try {
			PublicKeyDetails publicKey = publicKeyService.getPublicKey();
		
			response.put(Constants.MODULUS, publicKey.getModulus().toString());
			response.put(Constants.EXPONENT, publicKey.getExponent().toString());
			logger.info("End sendPublicKey");
			return response.toString();
		}catch(CertificateException | IOException ex) {
			logger.error("There was an error using the certificate to get the public key: ",ex);
			response.put(Constants.ERROR_RESPONSE_OBJECT, "Something went wrong on our end. Please contact the administrator for further details");
			return response.toString();
		}catch(NoSuchAlgorithmException ex){
			logger.error("No such algorithm exception when getting the public key details: ", ex);
			response.put(Constants.ERROR_RESPONSE_OBJECT,"Something went wrong on our end. Please contact the administrator for further details");
			return response.toString();
		}
		
	}
}
