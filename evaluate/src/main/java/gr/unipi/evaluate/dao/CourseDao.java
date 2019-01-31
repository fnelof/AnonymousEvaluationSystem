package gr.unipi.evaluate.dao;

import java.math.BigInteger;
import java.util.List;

import gr.unipi.evaluate.model.Course;

public interface CourseDao {
	public List<Course> getCoursesFromSyllabus(BigInteger id);
}
