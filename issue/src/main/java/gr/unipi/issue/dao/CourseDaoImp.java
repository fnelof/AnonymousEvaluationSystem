package gr.unipi.issue.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.unipi.issue.model.Course;

@Repository
public class CourseDaoImp implements CourseDao{
	@Autowired
	SessionFactory sessionFactory;
	// Fetches only the student's courses available for evaluation from the db
	@Override
	public List<Course> getNonIssuedCoursesFromUid(BigInteger uid) {
		Session session = sessionFactory.getCurrentSession();
		
		Query query =  session.createQuery("select course from CourseInstructorStudent where student_id=:studentId and issued=false");
		query.setParameter("studentId", uid);
		@SuppressWarnings("unchecked")
		List<Course> courseList = query.getResultList();
	
		return courseList;
	}

}
