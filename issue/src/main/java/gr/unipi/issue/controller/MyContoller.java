package gr.unipi.issue.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import gr.unipi.issue.service.TicketService;

@Controller
public class MyContoller {

  @Autowired
  TicketService ticketService;
  // Serves the index page
  @GetMapping("/")
  public ModelAndView index() {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("index");
    
    return modelAndView;
  }
}
