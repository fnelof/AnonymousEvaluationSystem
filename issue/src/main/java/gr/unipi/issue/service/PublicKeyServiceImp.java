package gr.unipi.issue.service;


import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.unipi.issue.dao.PublicKeyDetailsDao;
import gr.unipi.issue.model.PublicKeyDetails;

@Service
public class PublicKeyServiceImp implements PublicKeyService{
	@Autowired
	PublicKeyDetailsDao publicKeyDao;
	
	// Returns the public key details, modulus n and public exponent e
	@Override
	public PublicKeyDetails getPublicKey() throws CertificateException, IOException, NoSuchAlgorithmException{
		
		PublicKeyDetails publicKey = publicKeyDao.getPublicKeyDetails();
		
		return publicKey;
	}
	public static String getHexString(byte[] b) throws Exception {
        String result = "";
        for (int i=0; i < b.length; i++) {
            result +=
                Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
        }
        return result;
}
}
