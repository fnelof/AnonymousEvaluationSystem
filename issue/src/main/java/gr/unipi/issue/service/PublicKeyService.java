package gr.unipi.issue.service;


import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import gr.unipi.issue.model.PublicKeyDetails;

public interface PublicKeyService {
    PublicKeyDetails getPublicKey() throws CertificateException, IOException, NoSuchAlgorithmException;
    PublicKeyDetails getPublicKeyOfCourse(BigInteger courseId) throws CertificateException, IOException, NoSuchAlgorithmException, KeyStoreException;
}
