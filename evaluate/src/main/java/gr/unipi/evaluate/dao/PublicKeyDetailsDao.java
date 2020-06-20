package gr.unipi.evaluate.dao;

import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import gr.unipi.evaluate.model.PublicKeyDetails;

public interface PublicKeyDetailsDao {
	PublicKeyDetails getPublicKeyDetails() throws IOException, CertificateException;

	PublicKeyDetails getPublicKeyDetailsOfCourseInstructor(BigInteger courseId, BigInteger instructorId) throws IOException, CertificateException, KeyStoreException, NoSuchAlgorithmException;

	PublicKeyDetails getPublicKeyDetailsOfIssuerCourse(BigInteger courseId) throws IOException, CertificateException, KeyStoreException, NoSuchAlgorithmException;

	}
