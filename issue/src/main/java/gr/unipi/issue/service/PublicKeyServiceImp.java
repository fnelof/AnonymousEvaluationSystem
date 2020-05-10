package gr.unipi.issue.service;


import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.unipi.issue.dao.PublicKeyDetailsDao;
import gr.unipi.issue.model.PublicKeyDetails;

@Service
public class PublicKeyServiceImp implements PublicKeyService{

	private static final Logger logger = LogManager.getLogger(PublicKeyServiceImp.class);

	@Autowired
	PublicKeyDetailsDao publicKeyDao;
	
	// Returns the public key details, modulus n and public exponent e
	@Override
	public PublicKeyDetails getPublicKey() throws CertificateException, IOException, NoSuchAlgorithmException{
		logger.info("Start getPublicKey");
		PublicKeyDetails publicKey = publicKeyDao.getPublicKeyDetails();

		logger.info("End getPublicKey");
		return publicKey;
	}

	@Override
	public PublicKeyDetails getPublicKeyOfCourse(BigInteger courseId) throws CertificateException, IOException, NoSuchAlgorithmException, KeyStoreException {
		logger.info("Start getPublicKeyOfCourse, courseId: {}", courseId);
		PublicKeyDetails publicKeyDetails = publicKeyDao.getPublicKeyDetailsOfCourse(courseId);

		logger.info("End getPublicKeyOfCourse, courseId: {}", courseId);
		return publicKeyDetails;
	}

}
