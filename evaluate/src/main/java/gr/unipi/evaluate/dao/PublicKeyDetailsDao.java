package gr.unipi.evaluate.dao;

import java.io.IOException;
import java.security.cert.CertificateException;

import gr.unipi.evaluate.model.PublicKeyDetails;

public interface PublicKeyDetailsDao {
	PublicKeyDetails getPublicKeyDetails() throws IOException, CertificateException;

}
