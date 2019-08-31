package gr.unipi.evaluate.controller;

import gr.unipi.evaluate.common.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
	// Main controller that will serve the index page 
	@RequestMapping("*")
	public ModelAndView getIndex() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(Constants.INDEX);
		return modelAndView;
	}
}
