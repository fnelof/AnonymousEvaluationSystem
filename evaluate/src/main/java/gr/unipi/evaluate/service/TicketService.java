package gr.unipi.evaluate.service;

import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import gr.unipi.evaluate.model.Ticket;

public interface TicketService {
	public BigInteger generateTicket(String msg) throws NoSuchAlgorithmException;
	public String generateHash(String msg) throws NoSuchAlgorithmException;
	public boolean isValid(String msg, BigInteger signedTicket) throws NoSuchAlgorithmException, FileNotFoundException, CertificateException;
	public boolean isUsed(String ticket);
	public void submitTicket(Ticket t);
}
