package gr.unipi.evaluate.model;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="evaluations")
public class Evaluation {
	@EmbeddedId
	private EvaluationPK id;
	
	@Column(name="evaluation")
	private int evaluation;
	
	public Evaluation(String ticket, BigInteger courseId, BigInteger instructorId, int questionId, int evaluation) {
		
		this.id = new EvaluationPK(ticket,questionId);
		this.evaluation = evaluation;
		
	}
	public EvaluationPK getId() {
		return id;
	}

	public void setId(EvaluationPK id) {
		this.id = id;
	}

	public int getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
	}

	@Override
	public String toString() {
		return "Evaluation{" +
				"id=" + id +
				", evaluation=" + evaluation +
				'}';
	}
}
