package gr.unipi.issue.listeners;

import gr.unipi.issue.service.LoginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {

    private static final Logger logger = LogManager.getLogger(AuthenticationSuccessListener.class);

    @Autowired
    LoginService loginService;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent authenticationSuccessEvent) {
        String username = authenticationSuccessEvent.getAuthentication().getName();
        loginService.successfulLogin(username);
        logger.info("{} successfully logged in", username);
    }
}
