package gr.unipi.lecturer.service;


import gr.unipi.lecturer.dao.PrivateKeyDetailsDao;
import gr.unipi.lecturer.model.PrivateKeyDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    PrivateKeyDetailsDao privateKeyDetailsDao;

    @Override
    public BigInteger sign(BigInteger courseId, BigInteger instructorId, BigInteger ticket) throws
            UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
        PrivateKeyDetails privateKeyDetails = privateKeyDetailsDao.getPrivateKeyDetailsOfCourseAndInstructor(courseId,instructorId);

        BigInteger signature = ticket.modPow(privateKeyDetails.getPrivateExponent(), privateKeyDetails.getModulus());

        return signature;
    }
}
