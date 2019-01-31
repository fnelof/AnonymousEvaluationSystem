package gr.unipi.issue.dao;

import java.math.BigInteger;
import java.util.List;

import gr.unipi.issue.model.Course;

public interface CourseDao {
	public List<Course> getNonIssuedCoursesFromUid(BigInteger uid);
}
