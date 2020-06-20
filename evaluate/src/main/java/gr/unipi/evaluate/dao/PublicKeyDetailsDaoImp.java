package gr.unipi.evaluate.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import gr.unipi.evaluate.model.PublicKeyDetails;

@Repository
public class PublicKeyDetailsDaoImp implements PublicKeyDetailsDao{

	@Value("${public.key.path}")
	String publicKeyPath;

	@Value("${course.instructor.keystore.path}")
	String courseInstructorPath;

	@Value("${course.instructor.keystore.password}")
	String courseInstructorPassword;

	@Value("${issuer.keystore.filepath}")
	String issuerKeystorePath;

	@Value("${issuer.keystore.password}")
	String issuerKeystorePassword;
	/* 
	 * Fetches the public key details, modulus (n) and public exponent (e) of the certificate.
	 * n & e are needed for the verification of the signed ticket
	*/
	
	@Override
	public PublicKeyDetails getPublicKeyDetails() throws IOException, CertificateException {
		RSAPublicKey pk;

		try(FileInputStream fin = new FileInputStream(publicKeyPath)) {
			CertificateFactory f = CertificateFactory.getInstance("X.509");
			X509Certificate certificate = (X509Certificate) f.generateCertificate(fin);
			pk = (RSAPublicKey) certificate.getPublicKey();
		}

		return new PublicKeyDetails(pk.getModulus(), pk.getPublicExponent());
	}

	@Override
	public PublicKeyDetails getPublicKeyDetailsOfCourseInstructor(BigInteger courseId, BigInteger instructorId) throws IOException, CertificateException, KeyStoreException, NoSuchAlgorithmException {
		RSAPublicKey pk;
		String alias = courseId.toString() + "-" + instructorId.toString();

		try(FileInputStream fin = new FileInputStream(courseInstructorPath)) {
			KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
			keystore.load(fin,courseInstructorPassword.toCharArray());
			X509Certificate cert = (X509Certificate) keystore.getCertificate(alias);
			pk = (RSAPublicKey) cert.getPublicKey();
		}

		return new PublicKeyDetails(pk.getModulus(), pk.getPublicExponent());
	}

	@Override
	public PublicKeyDetails getPublicKeyDetailsOfIssuerCourse(BigInteger courseId) throws IOException, CertificateException, KeyStoreException, NoSuchAlgorithmException {
		RSAPublicKey pk;
		String alias = "public"+courseId.toString();

		try(FileInputStream fin = new FileInputStream(issuerKeystorePath)) {
			KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
			keystore.load(fin,issuerKeystorePassword.toCharArray());
			X509Certificate cert = (X509Certificate) keystore.getCertificate(alias);
			pk = (RSAPublicKey) cert.getPublicKey();
		}

		return new PublicKeyDetails(pk.getModulus(), pk.getPublicExponent());
	}

}
