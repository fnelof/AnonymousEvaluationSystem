package gr.unipi.issue.dao;

import java.math.BigInteger;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.unipi.issue.model.Student;

@Repository
public class StudentDaoImp implements StudentDao{
	@Autowired
	SessionFactory sessionFactory;
	
	// Fetches the id of the Student from the db
	@Override
	public BigInteger getUidFromUsername(String username) {

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Student where username=:username");
		query.setParameter("username", username);
		Student student = (Student) query.getSingleResult();
		return student.getId();
	}
	
}
