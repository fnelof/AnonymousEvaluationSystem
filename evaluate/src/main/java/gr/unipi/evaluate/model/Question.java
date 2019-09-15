package gr.unipi.evaluate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="questions")
public class Question {
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="question")
	private String text;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Question{" +
				"id=" + id +
				", question='" + text + '\'' +
				'}';
	}
}
