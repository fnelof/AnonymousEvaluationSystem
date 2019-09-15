package gr.unipi.evaluate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tickets")
public class Ticket {
	
	@Id
	@Column(name="ticket")
	private String ticketStringValue;
	
	@ManyToOne
    @JoinColumn(name="course_id", nullable=false)
	private Course course;
	
	@ManyToOne
    @JoinColumn(name="instructor_id", nullable=false)
	private Instructor instructor;
	
	@Column(name="comment")
	private String comment;

	public Ticket(){}

	public Ticket(String ticket,Course course, Instructor instructor, String comment){
		this.ticketStringValue = ticket;
		this.course = course;
		this.instructor = instructor;
		this.comment = comment;
	}
	
	public String getTicket() {
		return ticketStringValue;
	}

	public void setTicket(String ticketStringValue) {
		this.ticketStringValue = ticketStringValue;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Ticket{" +
				"ticket='" + ticketStringValue + '\'' +
				", course=" + course +
				", instructor=" + instructor +
				", comment='" + comment + '\'' +
				'}';
	}
}
