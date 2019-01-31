package gr.unipi.evaluate.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gr.unipi.evaluate.service.DepartmentService;

@RestController
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;
	
	// Handles the request for all the departments of the university

	@RequestMapping(value="/getDepartments", method = RequestMethod.POST)
	public String getDepartment() {
		JSONObject response = departmentService.getDepartmentList();
		return response.toString();
		
	}
}
