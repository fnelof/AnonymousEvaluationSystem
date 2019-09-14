package gr.unipi.issue.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gr.unipi.issue.dao.TicketDao;
import gr.unipi.issue.model.Ticket;

@Service
public class TicketServiceImp implements TicketService{

	private static final Logger logger = LogManager.getLogger(TicketServiceImp.class);
	
	@Autowired
	TicketDao ticketDao;
	@Transactional
	public Ticket getTicket(String ticket){
		logger.info("Start getTicket, ticket: {}",ticket);
		Ticket t = ticketDao.getTicket(ticket);
		logger.info("End getTicket, ticket: {}", ticket);
		return t;
	}
}
