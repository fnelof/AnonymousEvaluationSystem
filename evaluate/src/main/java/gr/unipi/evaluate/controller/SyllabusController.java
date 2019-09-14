package gr.unipi.evaluate.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import gr.unipi.evaluate.service.SyllabusService;

@RestController
public class SyllabusController {

	private static final Logger logger = LogManager.getLogger(SyllabusController.class);

	@Autowired
	SyllabusService syllabusService;
	
	// Fetches and serves all the syllabus that are related with the corresponding department
	@GetMapping(value="/getSyllabus")
	public String getSyllabusList(@RequestParam String department) {
		logger.info("Start getSyllabus for department: {}", department);
		
		JSONObject response = syllabusService.getSyllabusList(department);

		logger.info("End getSyllabus for department: {}", department);
		return response.toString();
	}

}
