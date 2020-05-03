package gr.unipi.issue.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPrivateKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import gr.unipi.issue.model.PrivateKeyDetails;

@Repository
public class PrivateKeyDetailsDaoImp implements PrivateKeyDetailsDao{
	@Value("${keystore.path}")
	String keystorePath;

	@Value("${keystore.password}")
	String keystorePassword;

	@Value("${keystore.certificate.alias}")
	String alias;

	@Value("${keystore.certificate.password}")
	String certificatePassword;
	/*
	 *  Fetches the private key details, modulus n and private exponent d from the keystore
	 *  These are needed for the signature of the blinded ticket
	 */
	@Override
	public PrivateKeyDetails getPrivateKeyDetails() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, UnrecoverableKeyException {
		RSAPrivateKey key;
		try(FileInputStream is = new FileInputStream(keystorePath)){
			KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
			keystore.load(is,keystorePassword.toCharArray());

			key = (RSAPrivateKey) keystore.getKey(alias, certificatePassword.toCharArray());
		}

		return new PrivateKeyDetails(key.getModulus(),key.getPrivateExponent());
	}

}
