package gr.unipi.issue.dao;

import gr.unipi.issue.model.Ticket;

public interface TicketDao {
	Ticket getTicket(String ticket);
}
