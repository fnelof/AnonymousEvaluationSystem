package gr.unipi.evaluate.service;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.persistence.NoResultException;

import gr.unipi.evaluate.common.Constants;
import gr.unipi.evaluate.model.Course;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gr.unipi.evaluate.dao.PublicKeyDetailsDao;
import gr.unipi.evaluate.dao.TicketDao;
import gr.unipi.evaluate.model.PublicKeyDetails;
import gr.unipi.evaluate.model.Ticket;

@Service
public class TicketServiceImp implements TicketService{

	private static final Logger logger = LogManager.getLogger(TicketServiceImp.class);

	@Autowired
	PublicKeyDetailsDao publicKeyDao;
	
	@Autowired
	TicketDao ticketDao;
	
	// Generates the ticket from the original message m (as a big integer) 
	@Override
	public BigInteger generateTicket(String msg) throws NoSuchAlgorithmException {
		logger.info("Start generateTicket, messsage: {}", msg);
		String hash = generateHash(msg);
		
		BigInteger bigInt = new BigInteger(hash.getBytes());
		logger.info("End generateTicket, message: {}, ticket: {}", msg, bigInt);
		return bigInt;
	}
	
	// Generates the hash of the original message m (SHA-256(m))
	@Override
	public String generateHash(String msg) throws NoSuchAlgorithmException {
		logger.info("Start generateHash, message: {}", msg);
		MessageDigest digest = MessageDigest.getInstance(Constants.HASH_ALGORITHM);
		byte[] hash = digest.digest(
		  msg.getBytes(StandardCharsets.UTF_8));
		String hashString = new String(Hex.encode(hash));
		logger.info("End generateHash, message: {}", msg);
		return hashString;
	}
	/*
	 *  Checks the validity of the signed ticket.
	 *  The condition for the signed ticket to be valid is:
	 *  verification(signedTicket) = h(m)
	*/
	@Transactional
	public boolean isValid(String msg, BigInteger signedTicket, Course course) throws NoSuchAlgorithmException,
			IOException, CertificateException, KeyStoreException {
		logger.info("Start isValid, message: {}, signedTicket: {}", msg, signedTicket);
		BigInteger ticket = calculateTicketOfEAT(msg, course.getNumberOfLectures());
		PublicKeyDetails publicKey = publicKeyDao.getPublicKeyDetailsOfIssuerCourse(course.getId());
		
		BigInteger verifiedTicket = signedTicket.modPow(publicKey.getExponent(), publicKey.getModulus());
		if(ticket.equals(verifiedTicket)) {
			logger.info("End isValid, result: {}, message: {}, signedTicket: {}", "true", msg, signedTicket);
			return true;
		}
		logger.warn("End isValid, result: {}, message: {}, signedTicket: {}", "false", msg, signedTicket);
		return false;
	}

	private BigInteger calculateTicketOfEAT(String ticket, int totalLectures) throws NoSuchAlgorithmException {

		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		String t = ticket;
		for(int i=0;i<totalLectures;i++){
			byte[] encodedhash = digest.digest(
					t.getBytes(StandardCharsets.UTF_8));
			t = bytesToHex(encodedhash);
		}

		return new BigInteger(t.getBytes());
	}

	//TODO put this in a UTILS file
	private static String bytesToHex(byte[] hash) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if(hex.length() == 1) hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}

	// Checks if the ticket is already used
	@Transactional
	public boolean isUsed(String ticket) {
		logger.info("Start isUsed, ticket: {}", ticket);
		try {
			ticketDao.isUsed(ticket);
			logger.info("End isUsed, result: {}, ticket: {}","true", ticket);
		}catch(NoResultException ex) {
			logger.info("End isUsed, no results for ticket: {}", ticket);
			return false;
		}catch(Exception ex){
			logger.error("An unkown error occured on isUsed", ex);
		}
		logger.warn("End isUsed, result: {}, ticket: {}","true", ticket);
		return true;
	}

	@Transactional
	public void submitTicket(Ticket t) {
		logger.info("Start submitTicket, ticket: {}", t);
		ticketDao.submitTicket(t);
		logger.info("End submitTicket, ticket: {}", t);
	}

}
