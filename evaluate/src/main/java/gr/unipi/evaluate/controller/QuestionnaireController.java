package gr.unipi.evaluate.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gr.unipi.evaluate.service.QuestionService;

@RestController
public class QuestionnaireController {
	@Autowired
	QuestionService questionService;
	
	// Handles the request for the questionnaire the student has to fill for the evaluation
	@RequestMapping(value="/getQuestionnaire", method = RequestMethod.POST)
	public String getQuestionnaire() {
		
		JSONObject response = questionService.getQuestionnaire();
		
		return response.toString();
	}
	
}
