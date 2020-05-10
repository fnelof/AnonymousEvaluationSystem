package gr.unipi.issue.dao;

import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import gr.unipi.issue.model.PublicKeyDetails;

public interface PublicKeyDetailsDao {
	PublicKeyDetails getPublicKeyDetails() throws IOException, CertificateException;

    PublicKeyDetails getPublicKeyDetailsOfCourse(BigInteger courseId) throws IOException, CertificateException, KeyStoreException, NoSuchAlgorithmException;
}
