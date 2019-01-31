package gr.unipi.evaluate.dao;


import org.hibernate.exception.ConstraintViolationException;
import org.mariadb.jdbc.internal.common.QueryException;
import org.springframework.dao.DataIntegrityViolationException;

import gr.unipi.evaluate.model.Evaluation;

public interface EvaluationDao {
	public void submitEvaluation(Evaluation evaluation) throws QueryException, ConstraintViolationException, DataIntegrityViolationException;
}
