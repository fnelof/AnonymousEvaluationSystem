package gr.unipi.evaluate.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;

import org.springframework.stereotype.Repository;

import gr.unipi.evaluate.model.PublicKeyDetails;

@Repository
public class PublicKeyDetailsDaoImp implements PublicKeyDetailsDao{

	/* 
	 * Fetches the public key details, modulus (n) and public exponent (e) of the certificate.
	 * n & e are needed for the verification of the signed ticket
	*/
	
	@Override
	public PublicKeyDetails getPublicKeyDetails() throws FileNotFoundException, CertificateException {

		String path = "/home/unipistudent/certificate/server.crt";
		
		FileInputStream fin = new FileInputStream(path);
		CertificateFactory f = CertificateFactory.getInstance("X.509");
		X509Certificate certificate = (X509Certificate)f.generateCertificate(fin);
		RSAPublicKey pk = (RSAPublicKey) certificate.getPublicKey();		
		
		PublicKeyDetails publicKey = new PublicKeyDetails(pk.getModulus(),pk.getPublicExponent());
		return publicKey;
	}

}
