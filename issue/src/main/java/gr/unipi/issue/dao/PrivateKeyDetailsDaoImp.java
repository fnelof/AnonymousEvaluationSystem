package gr.unipi.issue.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPrivateKey;

import org.springframework.stereotype.Repository;

import gr.unipi.issue.model.PrivateKeyDetails;

@Repository
public class PrivateKeyDetailsDaoImp implements PrivateKeyDetailsDao{

	/*
	 *  Fetches the private key details, modulus n and private exponent d from the keystore
	 *  These are needed for the signature of the blinded ticket
	 */
	@Override
	public PrivateKeyDetails getPrivateKeyDetails() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, UnrecoverableKeyException {
		   FileInputStream is = new FileInputStream("/opt/tomcat/.keystore");

		   KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
		   keystore.load(is, "secret".toCharArray());

		   String alias = "tomcat";

		   RSAPrivateKey key = (RSAPrivateKey) keystore.getKey(alias, "secret".toCharArray());
		   PrivateKeyDetails privateKey = new PrivateKeyDetails(key.getModulus(),key.getPrivateExponent());

		   return privateKey;
	}

}
