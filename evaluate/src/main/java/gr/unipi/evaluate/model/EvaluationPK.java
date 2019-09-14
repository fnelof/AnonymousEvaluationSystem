package gr.unipi.evaluate.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EvaluationPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name="ticket")
	private String ticket;
	
	@Column(name="question_id")
	private int questionId;
	
	public EvaluationPK(String ticket, int questionId) {
		this.ticket = ticket;
		this.questionId = questionId;
		
	}
	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	@Override
	public String toString() {
		return "EvaluationPK{" +
				"ticket='" + ticket + '\'' +
				", questionId=" + questionId +
				'}';
	}
}
