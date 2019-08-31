package gr.unipi.evaluate.service;

import java.util.List;

import gr.unipi.evaluate.common.Constants;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gr.unipi.evaluate.dao.QuestionDao;
import gr.unipi.evaluate.model.Question;

@Service
public class QuestionServiceImp implements QuestionService{
	@Autowired
	QuestionDao questionDao;
	
	/*
	 * Creates the json object of the questionnaire 
	 * needed to be filled for evaluation from the Student
	*/
	@Transactional
	public JSONObject getQuestionnaire() {

		List<Question> questionnaire = questionDao.getQuestionnaire();
		JSONObject jsonObject = new JSONObject();
		JSONArray questionList = new JSONArray();
		for(Question q: questionnaire) {
			JSONObject question = new JSONObject();
			question.put(Constants.ID, q.getId());
			question.put(Constants.QUESTION, q.getQuestion());
			questionList.put(question);
		}
		jsonObject.put(Constants.QUESTIONNAIRE, questionList);
		return jsonObject;
	}

}
