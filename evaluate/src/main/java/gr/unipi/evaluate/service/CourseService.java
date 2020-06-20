package gr.unipi.evaluate.service;

import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.List;

import org.json.JSONObject;

public interface CourseService {
	JSONObject getCoursesFromSyllabus(BigInteger id);
	boolean isEligible(List<BigInteger> ticketChain, String initialTicket, BigInteger courseId,  BigInteger instructorId) throws IOException, CertificateException, KeyStoreException, NoSuchAlgorithmException;
}
