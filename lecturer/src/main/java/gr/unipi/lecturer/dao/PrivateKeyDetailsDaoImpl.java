package gr.unipi.lecturer.dao;

import gr.unipi.lecturer.model.PrivateKeyDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPrivateKey;


@Repository
public class PrivateKeyDetailsDaoImpl implements PrivateKeyDetailsDao {

    @Value("${keystore.path}")
    String keystorePath;

    @Value("${keystore.password}")
    String keystorePassword;

    @Value("${keystore.certificate.password}")
    String certificatePassword;

    @Value("${course.keystore.filepath}")
    String courseInstructorKeystorePath;



    @Override
    public PrivateKeyDetails getPrivateKeyDetailsOfCourseAndInstructor(BigInteger courseId, BigInteger instructorId) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, UnrecoverableKeyException {


        RSAPrivateKey key;
        try(FileInputStream is = new FileInputStream(courseInstructorKeystorePath)){
            KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            keystore.load(is,keystorePassword.toCharArray());

            key = (RSAPrivateKey) keystore.getKey(courseId.toString()+"-"+instructorId.toString(), certificatePassword.toCharArray());
        }

        return new PrivateKeyDetails(key.getModulus(),key.getPrivateExponent());
    }
}
