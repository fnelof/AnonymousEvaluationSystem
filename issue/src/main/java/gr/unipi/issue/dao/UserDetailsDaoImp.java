package gr.unipi.issue.dao;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.unipi.issue.model.Student;

@Repository
public class UserDetailsDaoImp implements UserDetailsDao{
	private static final Logger logger = LogManager.getLogger(UserDetailsDaoImp.class);
	@Autowired
	private SessionFactory sessionFactory;

	// Fetches the student from db according to his username
	public Student findUserByUsername(String username) {
		logger.info("Start findUserByUsername, username: {}", username);
		Session session = sessionFactory.getCurrentSession();

		CriteriaBuilder criteriaBuilder  = session.getCriteriaBuilder();
		CriteriaQuery<Student> query = criteriaBuilder.createQuery(Student.class);
		
		Root<Student> root = query.from(Student.class);
		query.select(root).where(criteriaBuilder.equal(root.get("username"), username));

		Query<Student> q = session.createQuery(query);
		
		Student user =  q.getSingleResult();
		logger.info("End findUserByUsername, username: {}", username);
		return user;
	}
}
