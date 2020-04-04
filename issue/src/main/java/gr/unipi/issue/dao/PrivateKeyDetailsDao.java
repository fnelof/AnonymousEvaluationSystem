package gr.unipi.issue.dao;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import org.springframework.stereotype.Repository;

import gr.unipi.issue.model.PrivateKeyDetails;

@Repository
public interface PrivateKeyDetailsDao {
	PrivateKeyDetails getPrivateKeyDetails() throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, UnrecoverableKeyException;
}
