package gr.unipi.evaluate.dao;

import java.util.ArrayList;
import java.util.List;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import gr.unipi.evaluate.model.Syllabus;

@Repository
public class SyllabusDaoImp implements SyllabusDao{
	@Autowired
	SessionFactory sessionFactory;
	
	// Fetches the department's list of syllabus from the db
	@Override
	public List<Syllabus> getSyllabusList(String department) {
		Session session = sessionFactory.getCurrentSession();
		
		@SuppressWarnings("unchecked")
		Query<Syllabus> query = session.createQuery("from Syllabus where department.name=:dept");
		query.setParameter("dept", department);
        List<Syllabus> syllabusList = new ArrayList<Syllabus>();
        syllabusList = query.getResultList();
       
		return syllabusList;
	}

}
