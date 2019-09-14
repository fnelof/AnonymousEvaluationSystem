package gr.unipi.issue.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gr.unipi.issue.service.CourseService;

@RestController
public class CourseController {

	private static final Logger logger = LogManager.getLogger(CourseController.class);

	@Autowired
	CourseService courseService;

	// Handles the request for the student's courses
	@RequestMapping(value="/getCourses", method = RequestMethod.GET)
	public String getCourses() {
		logger.info("Start getCourses");

		JSONObject response = courseService.getCourses();

		logger.info("End getCourses");
		return  response.toString();
	}
	
}
