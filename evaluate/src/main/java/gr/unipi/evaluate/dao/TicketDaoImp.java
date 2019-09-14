package gr.unipi.evaluate.dao;

import javax.persistence.NoResultException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.unipi.evaluate.model.Ticket;

@Repository
public class TicketDaoImp implements TicketDao{

	private static final Logger logger = LogManager.getLogger(TicketDaoImp.class);

	@Autowired
	SessionFactory sessionFactory;
	
	// Checks if the ticket is already used
	@Override
	public void isUsed(String ticket) throws NoResultException {

		logger.info("Start isUsed, ticket: {}", ticket);

		Session session = sessionFactory.getCurrentSession();
		
		@SuppressWarnings("unchecked")
		Query<Ticket> query = session.createQuery("from Ticket where ticket=:ticket");
		query.setParameter("ticket", ticket);
		query.getSingleResult();

		logger.info("End isUsed, ticket: {}", ticket);
	}

	// Submits the ticket on the db so that it can't be used again
	@Override
	public boolean submitTicket(Ticket ticket) {
		logger.info("Start submitTicket, ticket: {}", ticket);
		Session session = sessionFactory.getCurrentSession();
		session.save(ticket);

		logger.info("End submitTicket, ticket: {}", ticket);
		return true;
	}
	
}
