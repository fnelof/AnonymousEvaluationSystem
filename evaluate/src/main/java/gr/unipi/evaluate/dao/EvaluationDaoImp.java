package gr.unipi.evaluate.dao;

import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.mariadb.jdbc.internal.common.QueryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import gr.unipi.evaluate.model.Evaluation;

@Repository
public class EvaluationDaoImp implements EvaluationDao{

	@Autowired
	SessionFactory sessionFactory;
	
	// Submits the evaluation of the user for one of the instructors and his course
	@Override
	public void submitEvaluation(Evaluation evaluation) throws QueryException, ConstraintViolationException, DataIntegrityViolationException{
		
		sessionFactory.getCurrentSession().save(evaluation);
		
	}
	
}
