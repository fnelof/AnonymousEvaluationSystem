package gr.unipi.issue.dao;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.unipi.issue.model.Ticket;


@Repository
public class TicketDaoImp implements TicketDao{
	@Autowired
	SessionFactory sessionFactory;
	
	// Fetches the ticket from the db
	@Override
	public Ticket getTicket(String ticket) {

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Ticket where ticket=:ticket");
		query.setParameter("ticket", ticket);
		Ticket t = (Ticket)query.getSingleResult();
		return t;
	}
	
}
