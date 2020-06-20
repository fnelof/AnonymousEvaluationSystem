package gr.unipi.evaluate.dao;

import java.math.BigInteger;
import java.util.List;

import gr.unipi.evaluate.model.Course;

public interface CourseDao {
	List<Course> getCoursesFromSyllabus(BigInteger id);

	Course findCourseById(BigInteger id);
}
