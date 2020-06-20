package gr.unipi.issue.dao;

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

import gr.unipi.issue.model.PublicKeyDetails;

@Repository
public class PublicKeyDetailsDaoImp implements PublicKeyDetailsDao{
	@Value("${public.key.path}")
	String publicKeyPath;

	@Value("${course.keystore.filepath}")
	String courseKeystorePath;

	@Value("${course.keystore.password}")
	String keystorePassword;
	/*
	 *  Fetches the public key details, modulus n and public exponent e from the certificate
	 *	These are needed from the front end to blind the ticket.
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
	public PublicKeyDetails getPublicKeyDetailsOfCourse(BigInteger courseId) throws IOException, CertificateException, KeyStoreException, NoSuchAlgorithmException {
		RSAPublicKey pk;
		String alias = "public"+courseId.toString();

		try(FileInputStream fin = new FileInputStream(courseKeystorePath)) {
				KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
				keystore.load(fin,keystorePassword.toCharArray());
				X509Certificate cert = (X509Certificate) keystore.getCertificate(alias);
				pk = (RSAPublicKey) cert.getPublicKey();
		}

		return new PublicKeyDetails(pk.getModulus(), pk.getPublicExponent());
	}


}
