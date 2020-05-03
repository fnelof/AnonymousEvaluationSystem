package gr.unipi.evaluate.dao;

import java.io.FileInputStream;
import java.io.IOException;
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

}
