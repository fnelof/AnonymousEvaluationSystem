package gr.unipi.issue.service;

import gr.unipi.issue.common.Constants;
import gr.unipi.issue.dao.UserDetailsDao;
import gr.unipi.issue.model.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class LoginServiceImp implements LoginService {

    private static final Logger logger = LogManager.getLogger(LoginServiceImp.class);

    @Autowired
    UserDetailsDao userDetailsDao;

    @Transactional
    @Override
    public int getUserLoginAttempts(String username) {
        logger.info("Start getUserLoginAttempts, username: {}", username);
        Student student = userDetailsDao.findUserByUsername(username);
        logger.info("End getUserLoginAttempts, username: {}", username);
        return student.getLoginAttempts();
    }

    @Transactional
    @Override
    public void updateLoginAttempts(String username) {
        logger.info("Start updateLoginAttempts, username: {}", username);
        Student student = userDetailsDao.findUserByUsername(username);
        student.setLoginAttempts(student.getLoginAttempts()+1);
        if(student.getLoginAttempts() >= Constants.MAXIMUM_LOGIN_ATTEMPTS){
            logger.warn("{} - account is now blocked", username);
            student.setEnabled(false);
        }
        logger.info("Start updateLoginAttempts, username: {}", username);
    }

    @Transactional
    @Override
    public void successfulLogin(String username) {
        logger.info("Start successfulLogin, username: {}", username);
        Student student = userDetailsDao.findUserByUsername(username);
        student.setEnabled(true);
        student.setLoginAttempts(0);
        logger.info("End successfulLogin, username: {}", username);
    }

}
