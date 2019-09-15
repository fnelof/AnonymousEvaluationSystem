package gr.unipi.evaluate.dao;

import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import gr.unipi.evaluate.model.Syllabus;

@Repository
public class SyllabusDaoImp implements SyllabusDao{

	private static final Logger logger = LogManager.getLogger(SyllabusDaoImp.class);

	@Autowired
	SessionFactory sessionFactory;
	
	// Fetches the department's list of syllabus from the db
	@Override
	public List<Syllabus> getSyllabusList(String department) {

		logger.info("Start getSyllabusList, department: {}", department);
		Session session = sessionFactory.getCurrentSession();
		
		@SuppressWarnings("unchecked")
		Query<Syllabus> query = session.createQuery("from Syllabus where department.name=:dept");
		query.setParameter("dept", department);
        List<Syllabus> syllabusList = query.getResultList();

		logger.info("End getSyllabusList, department: {}", department);

		return syllabusList;
	}

}
