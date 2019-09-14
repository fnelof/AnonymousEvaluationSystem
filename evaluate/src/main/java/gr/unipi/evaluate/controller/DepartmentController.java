package gr.unipi.evaluate.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gr.unipi.evaluate.service.DepartmentService;

@RestController
public class DepartmentController {

	private static final Logger logger = LogManager.getLogger(DepartmentController.class);

	@Autowired
	DepartmentService departmentService;
	
	// Handles the request for all the departments of the university

	@RequestMapping(value="/getDepartments", method = RequestMethod.POST)
	public String getDepartments() {
		logger.info("Start getDepartments");
		JSONObject response = departmentService.getDepartmentList();

		logger.info("End getDepartments");
		return response.toString();
		
	}
}
