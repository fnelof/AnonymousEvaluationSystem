package gr.unipi.issue.listeners;

import gr.unipi.issue.dao.UserDetailsDao;
import gr.unipi.issue.service.LoginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    private static final Logger logger = LogManager.getLogger(AuthenticationFailureListener.class);

    @Autowired
    LoginService loginService;

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent authenticationFailureBadCredentialsEvent) {

        String username = authenticationFailureBadCredentialsEvent.getAuthentication().getName();
        logger.warn("{} failed to login",username);
        loginService.updateLoginAttempts(username);
    }
}
