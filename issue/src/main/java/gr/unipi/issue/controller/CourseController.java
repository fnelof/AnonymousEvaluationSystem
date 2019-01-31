package gr.unipi.issue.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gr.unipi.issue.service.CourseService;

@RestController
public class CourseController {
	@Autowired
	CourseService courseService;
	
	// Handles the request for the student's courses
	@RequestMapping(value="/getCourses", method = RequestMethod.GET)
	public String getCourses() {
		
		JSONObject response = courseService.getCourses();
		return  response.toString();
	}
	
}
