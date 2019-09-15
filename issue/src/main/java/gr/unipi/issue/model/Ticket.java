package gr.unipi.issue.model;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name="tickets")
public class Ticket {
	
	@Id
	@Column(name="ticket")
	private BigInteger ticketId;
	
		
	public BigInteger getTicketId() {
		return ticketId;
	}
	public void setTicketId(BigInteger ticket) {
		this.ticketId = ticket;
	}


}
