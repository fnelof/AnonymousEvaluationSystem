package gr.unipi.issue.dao;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.unipi.issue.model.Ticket;


@Repository
public class TicketDaoImp implements TicketDao{

	private static final Logger logger = LogManager.getLogger(TicketDaoImp.class);

	@Autowired
	SessionFactory sessionFactory;
	
	// Fetches the ticket from the db
	@Override
	public Ticket getTicket(String ticket) {
		logger.info("Start getTicket, ticket: {}", ticket);
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Ticket where ticket=:ticket");
		query.setParameter("ticket", ticket);
		Ticket t = (Ticket)query.getSingleResult();
		logger.info("End getTicket, ticket: {}",ticket);
		return t;
	}
	
}
