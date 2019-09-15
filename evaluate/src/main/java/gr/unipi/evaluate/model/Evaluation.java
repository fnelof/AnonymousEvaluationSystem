package gr.unipi.evaluate.model;

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
	private int evaluationValue;
	
	public Evaluation(String ticket, int questionId, int evaluation) {
		
		this.id = new EvaluationPK(ticket,questionId);
		this.evaluationValue = evaluation;
		
	}
	public EvaluationPK getId() {
		return id;
	}

	public void setId(EvaluationPK id) {
		this.id = id;
	}

	public int getEvaluationValue() {
		return evaluationValue;
	}

	public void setEvaluationValue(int evaluationValue) {
		this.evaluationValue = evaluationValue;
	}

	@Override
	public String toString() {
		return "Evaluation{" +
				"id=" + id +
				", evaluation=" + evaluationValue +
				'}';
	}
}
