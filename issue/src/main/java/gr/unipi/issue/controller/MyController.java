package gr.unipi.issue.controller;


import gr.unipi.issue.common.Constants;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import gr.unipi.issue.service.TicketService;

@Controller
public class MyController {

  private static final Logger logger = LogManager.getLogger(MyController.class);

  @Autowired
  TicketService ticketService;
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
