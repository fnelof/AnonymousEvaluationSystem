package gr.unipi.evaluate.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.unipi.evaluate.model.Question;

@Repository
public class QuestionDaoImp implements QuestionDao{
	@Autowired
	SessionFactory sessionFactory;
	
	// Fetches the questionnaire from the db
	@Override
	public List<Question> getQuestionnaire() {

		Session session = sessionFactory.getCurrentSession();
		
		Query q = session.createQuery("from Question");
		
		@SuppressWarnings("unchecked")
		List<Question> questionnaire = q.getResultList();
		
		return questionnaire;
	}
	
}
