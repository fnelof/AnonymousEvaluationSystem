package gr.unipi.issue.service;

import gr.unipi.issue.common.Constants;
import gr.unipi.issue.dao.UserDetailsDao;
import gr.unipi.issue.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class LoginServiceImp implements LoginService {

    @Autowired
    UserDetailsDao userDetailsDao;

    @Transactional
    @Override
    public int getUserLoginAttempts(String username) {
        Student student = userDetailsDao.findUserByUsername(username);
        return student.getLoginAttempts();
    }

    @Transactional
    @Override
    public void updateLoginAttempts(String username) {
        Student student = userDetailsDao.findUserByUsername(username);
        student.setLoginAttempts(student.getLoginAttempts()+1);
        if(student.getLoginAttempts() >= Constants.MAXIMUM_LOGIN_ATTEMPTS){
            student.setEnabled(false);
        }
    }

    @Transactional
    @Override
    public void successfulLogin(String username) {
        Student student = userDetailsDao.findUserByUsername(username);
        student.setEnabled(true);
        student.setLoginAttempts(0);
    }

}
