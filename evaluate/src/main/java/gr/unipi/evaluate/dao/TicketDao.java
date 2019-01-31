package gr.unipi.evaluate.dao;

import gr.unipi.evaluate.model.Ticket;

public interface TicketDao {
	public void isUsed(String ticket);
	public boolean submitTicket(Ticket ticket);
}
