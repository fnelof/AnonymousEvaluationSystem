package gr.unipi.issue.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController{

    private static final Logger logger = LogManager.getLogger(LoginController.class);

    @GetMapping(value = "/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                                  @RequestParam(value = "logout", required = false) String logout,
                                  Model model) {
        logger.info("Start loginPage");

        if(error != null) {
            logger.error("Username or password are incorrect");
            return "loginError";
        }
        if(logout != null) {
            logger.info("Successfully logged out");
            model.addAttribute("logoutSuccess", "Successfully logged out");
        }
        logger.info("End loginPage");
        return "login";
    }
}
