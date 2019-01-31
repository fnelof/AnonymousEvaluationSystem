package gr.unipi.issue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gr.unipi.issue.dao.TicketDao;
import gr.unipi.issue.model.Ticket;

@Service
public class TicketServiceImp implements TicketService{
	
	@Autowired
	TicketDao ticketDao;
	@Transactional
	public Ticket getTicket(String ticket){
		Ticket t = ticketDao.getTicket(ticket);
		return t;
	}


}
