package gr.unipi.issue.service;


import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import gr.unipi.issue.model.PublicKeyDetails;

public interface PublicKeyService {
	public PublicKeyDetails getPublicKey() throws CertificateException, IOException, NoSuchAlgorithmException;
}
