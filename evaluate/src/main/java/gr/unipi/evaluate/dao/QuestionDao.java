package gr.unipi.evaluate.dao;

import java.util.List;

import gr.unipi.evaluate.model.Question;

public interface QuestionDao {
	public List<Question> getQuestionnaire();
}
