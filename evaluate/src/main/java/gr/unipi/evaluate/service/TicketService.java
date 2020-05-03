package gr.unipi.evaluate.service;

import java.io.IOException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import gr.unipi.evaluate.model.Ticket;

public interface TicketService {
	BigInteger generateTicket(String msg) throws NoSuchAlgorithmException;
	String generateHash(String msg) throws NoSuchAlgorithmException;
	boolean isValid(String msg, BigInteger signedTicket) throws NoSuchAlgorithmException, IOException, CertificateException;
	boolean isUsed(String ticket);
	void submitTicket(Ticket t);
}
