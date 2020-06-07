package gr.unipi.lecturer.controller;


import gr.unipi.lecturer.common.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {

  private static final Logger logger = LogManager.getLogger(MyController.class);

  // Serves the index page
  @GetMapping("/")
  public ModelAndView index() {
    logger.info("Start index");
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName(Constants.INDEX);

    logger.info("End index");
    return modelAndView;
  }
}
