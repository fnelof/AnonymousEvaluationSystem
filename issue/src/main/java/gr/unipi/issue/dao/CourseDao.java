package gr.unipi.issue.dao;

import java.math.BigInteger;
import java.util.List;

import gr.unipi.issue.model.Course;

public interface CourseDao {
	List<Course> getNonIssuedCoursesFromUid(BigInteger uid);
	Course getCourseFromId(BigInteger id);
}
