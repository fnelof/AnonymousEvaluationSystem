package gr.unipi.issue.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.unipi.issue.dao.PrivateKeyDetailsDao;
import gr.unipi.issue.model.PrivateKeyDetails;

@Service
public class PrivateKeyDetailsServiceImp implements PrivateKeyDetailsService{
	@Autowired
	PrivateKeyDetailsDao privateKeyDetailsDao;
	
	// Signs the blinded ticket
	@Override
	public BigInteger signMessage(BigInteger message) throws UnrecoverableKeyException, FileNotFoundException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {

		PrivateKeyDetails privateKey = privateKeyDetailsDao.getPrivateKeyDetails();
		BigInteger blindedSignedTicket = message.modPow(privateKey.getExponent(), privateKey.getModulus());
				
		return blindedSignedTicket;
	}

}
