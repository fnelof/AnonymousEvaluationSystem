package gr.unipi.evaluate.dao;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.unipi.evaluate.model.Ticket;

@Repository
public class TicketDaoImp implements TicketDao{
	@Autowired
	SessionFactory sessionFactory;
	
	// Checks if the ticket is already used
	@Override
	public void isUsed(String ticket) throws NoResultException {
		Session session = sessionFactory.getCurrentSession();
		
		@SuppressWarnings("unchecked")
		Query<Ticket> query = session.createQuery("from Ticket where ticket=:ticket");
		query.setParameter("ticket", ticket);
		query.getSingleResult();
		
			
   	}

	// Submits the ticket on the db so that it can't be used again
	@Override
	public boolean submitTicket(Ticket ticket) {

		Session session = sessionFactory.getCurrentSession();
		session.save(ticket);
		
		return true;
	}
	
}
