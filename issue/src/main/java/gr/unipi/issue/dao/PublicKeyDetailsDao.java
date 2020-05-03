package gr.unipi.issue.dao;

import java.io.IOException;
import java.security.cert.CertificateException;

import gr.unipi.issue.model.PublicKeyDetails;

public interface PublicKeyDetailsDao {
	PublicKeyDetails getPublicKeyDetails() throws IOException, CertificateException;
}
