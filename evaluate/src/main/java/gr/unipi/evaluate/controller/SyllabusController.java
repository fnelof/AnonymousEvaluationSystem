package gr.unipi.evaluate.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gr.unipi.evaluate.service.SyllabusService;

@RestController
public class SyllabusController {
	@Autowired
	SyllabusService syllabusService;
	
	// Fetches and serves all the syllabus that are related with the corresponding department
	@RequestMapping(value="/getSyllabus", method = RequestMethod.POST)
	public String getSyllabusList(@RequestParam String department) {
		
		JSONObject response = syllabusService.getSyllabusList(department);
		
		return response.toString();
	}

}
