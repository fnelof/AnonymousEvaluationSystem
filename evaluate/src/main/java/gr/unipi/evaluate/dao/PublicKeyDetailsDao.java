package gr.unipi.evaluate.dao;

import java.io.FileNotFoundException;
import java.security.cert.CertificateException;

import gr.unipi.evaluate.model.PublicKeyDetails;

public interface PublicKeyDetailsDao {
	PublicKeyDetails getPublicKeyDetails() throws FileNotFoundException, CertificateException;

}
