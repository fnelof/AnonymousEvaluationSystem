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
	private BigInteger ticket;
	
		
	public BigInteger getTicket() {
		return ticket;
	}
	public void setTicket(BigInteger ticket) {
		this.ticket = ticket;
	}


}
