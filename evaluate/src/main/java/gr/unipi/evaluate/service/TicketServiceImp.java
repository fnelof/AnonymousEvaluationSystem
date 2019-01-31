package gr.unipi.evaluate.service;

import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.persistence.NoResultException;

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
	@Autowired
	PublicKeyDetailsDao publicKeyDao;
	
	@Autowired
	TicketDao ticketDao;
	
	// Generates the ticket from the original message m (as a big integer) 
	@Override
	public BigInteger generateTicket(String msg) throws NoSuchAlgorithmException {
		String hash = generateHash(msg);
		
		BigInteger bigInt = new BigInteger(hash.getBytes());
		return bigInt;
	}
	
	// Generates the hash of the original message m (SHA-256(m))
	@Override
	public String generateHash(String msg) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(
		  msg.getBytes(StandardCharsets.UTF_8));
		String hashString = new String(Hex.encode(hash));
		return hashString;
	}
	/*
	 *  Checks the validity of the signed ticket.
	 *  The condition for the signed ticket to be valid is:
	 *  verification(signedTicket) = h(m)
	*/
	@Override
	public boolean isValid(String msg, BigInteger signedTicket) throws NoSuchAlgorithmException, FileNotFoundException, CertificateException {
		
		BigInteger ticket = generateTicket(msg);
		PublicKeyDetails publicKey = publicKeyDao.getPublicKeyDetails();
		
		BigInteger verifiedTicket = signedTicket.modPow(publicKey.getExponent(), publicKey.getModulus());
		if(ticket.equals(verifiedTicket)) {
			return true;
		}
		
		return false;
	}
	// Checks if the ticket is already used
	@Transactional
	public boolean isUsed(String ticket) {
		try {

			ticketDao.isUsed(ticket);
			return true;
		}catch(NoResultException ex) {
			
			return false;
		}
	}

	@Transactional
	public void submitTicket(Ticket t) {
		ticketDao.submitTicket(t);
	}

}
