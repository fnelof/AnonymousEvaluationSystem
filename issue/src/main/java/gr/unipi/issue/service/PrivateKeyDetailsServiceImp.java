package gr.unipi.issue.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.unipi.issue.dao.PrivateKeyDetailsDao;
import gr.unipi.issue.model.PrivateKeyDetails;

@Service
public class PrivateKeyDetailsServiceImp implements PrivateKeyDetailsService{

	private static final Logger logger = LogManager.getLogger(PrivateKeyDetailsServiceImp.class);

	@Autowired
	PrivateKeyDetailsDao privateKeyDetailsDao;
	
	// Signs the blinded ticket
	@Override
	public BigInteger signMessage(BigInteger message) throws UnrecoverableKeyException, FileNotFoundException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		logger.info("Start signMessage, message {}", message);

		PrivateKeyDetails privateKey = privateKeyDetailsDao.getPrivateKeyDetails();
		BigInteger blindedSignedTicket = message.modPow(privateKey.getExponent(), privateKey.getModulus());

		logger.info("End signMessage, message {}", message);
		return blindedSignedTicket;
	}

}
