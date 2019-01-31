package gr.unipi.evaluate.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.unipi.evaluate.model.Department;

@Repository
public class DepartmentDaoImp implements DepartmentDao{

	@Autowired
	SessionFactory sessionFactory;
	
	// Fetches all the departments of the university from the db
	
	@Override
	public List<Department> getDepartmentList() {

		Session session = sessionFactory.getCurrentSession();

	    CriteriaBuilder builder = session.getCriteriaBuilder();
	    CriteriaQuery<Department> query = builder.createQuery(Department.class);
	    Root<Department> variableRoot = query.from(Department.class);
	    query.select(variableRoot);
	    
		return session.createQuery(query).getResultList();
	}

}
