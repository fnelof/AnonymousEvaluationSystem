package gr.unipi.evaluate.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.unipi.evaluate.model.Course;


@Repository
public class CourseDaoImp implements CourseDao{
	@Autowired
	SessionFactory sessionFactory;
	
	// Fetches all the courses of a syllabus from the db
	
	@Override
	public List<Course> getCoursesFromSyllabus(BigInteger id) {

		Session session = sessionFactory.getCurrentSession();
		
		@SuppressWarnings("unchecked")
		Query<Course> query = session.createQuery("from Course where syllabus.id=:id");
		query.setParameter("id", id);
        List<Course> courseList = new ArrayList<Course>();
        courseList = query.getResultList();		
		
		return courseList;
	}
}
