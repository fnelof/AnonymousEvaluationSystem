package gr.unipi.issue.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.unipi.issue.model.Course;

@Repository
public class CourseDaoImp implements CourseDao{

	private static final Logger logger = LogManager.getLogger(CourseDaoImp.class);

	@Autowired
	SessionFactory sessionFactory;
	// Fetches only the student's courses available for evaluation from the db
	@Override
	public List<Course> getNonIssuedCoursesFromUid(BigInteger uid) {
		logger.info("Start getNonIssuedCoursesFromUid: uid {}", uid);
		Session session = sessionFactory.getCurrentSession();
		
		Query query =  session.createQuery("select course from CourseInstructorStudent where student_id=:studentId and issued=false");
		query.setParameter("studentId", uid);
		@SuppressWarnings("unchecked")
		List<Course> courseList = query.getResultList();
		logger.info("End getNonIssuedCoursesFromUid: uid {}", uid);
		return courseList;
	}

	public Course getCourseFromId(BigInteger id){
		logger.info("Start getCourseFromId: id {}", id);
		Session session = sessionFactory.getCurrentSession();

		Query query =  session.createQuery("from Course where id=:courseId");
		query.setParameter("courseId", id);
		Course course= (Course) query.getSingleResult();
		logger.info("End getCourseFromId: id {}", id);
		return course;
	}

}
