package gr.unipi.issue.controller;

import java.io.IOException;

import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.unipi.issue.model.PublicKeyDetails;
import gr.unipi.issue.service.PublicKeyService;

@RestController
public class PublicKeyController {
	@Autowired
	PublicKeyService publicKeyService;
	
	/*
	 * Handles the request for the public key details
	 * The public key is needed to encrypt and blind the ticket 
	*/
	@RequestMapping("/sendPublicKey")
	public String sendPublicKey() {

		JSONObject response =  new JSONObject();
		
		try {
			PublicKeyDetails publicKey = publicKeyService.getPublicKey();
		
			response.put("modulus", publicKey.getModulus().toString());
			response.put("exponent", publicKey.getExponent().toString());
	
			return response.toString();
		}catch(CertificateException | IOException | NoSuchAlgorithmException ex) {
			System.out.println(ex.toString());
			response.put("error", "Something went wrong on our end. Please contact the administrator for further details");
			return response.toString();
		}
		
	}
}
