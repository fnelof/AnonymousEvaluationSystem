package gr.unipi.lecturer.dao;

import gr.unipi.lecturer.model.PrivateKeyDetails;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

public interface PrivateKeyDetailsDao {

    PrivateKeyDetails getPrivateKeyDetailsOfCourseAndInstructor(BigInteger courseId, BigInteger instructorId) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, UnrecoverableKeyException;

}
