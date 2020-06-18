package gr.unipi.lecturer.service;

import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

public interface TicketService {
    BigInteger sign(BigInteger courseId, BigInteger instructorId, BigInteger ticket) throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException;
}
