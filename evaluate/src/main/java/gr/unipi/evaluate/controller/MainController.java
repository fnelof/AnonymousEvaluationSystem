package gr.unipi.evaluate.controller;

import gr.unipi.evaluate.common.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	private static final Logger logger = LogManager.getLogger(MainController.class);

	// Main controller that will serve the index page 
	@RequestMapping("*")
	public ModelAndView getIndex() {
		logger.info("Start getIndex");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(Constants.INDEX);
		logger.info("End getIndex");
		return modelAndView;
	}
}
