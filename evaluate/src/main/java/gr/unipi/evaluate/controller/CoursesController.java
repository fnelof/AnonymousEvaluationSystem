package gr.unipi.evaluate.controller;

import java.math.BigInteger;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gr.unipi.evaluate.service.CourseService;

@RestController
public class CoursesController {
	@Autowired
	CourseService courseService;
	
	// Handles the request for the courses of a syllabus

	@RequestMapping(value = "/getCourses", method=RequestMethod.POST)
	public String getCourses(@RequestParam BigInteger syllabusId) {
		JSONObject response = courseService.getCoursesFromSyllabus(syllabusId);
		
		return response.toString();
	}
}
