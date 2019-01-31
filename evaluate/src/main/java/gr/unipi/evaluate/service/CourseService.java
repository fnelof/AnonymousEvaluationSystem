package gr.unipi.evaluate.service;

import java.math.BigInteger;

import org.json.JSONObject;

public interface CourseService {
	public JSONObject getCoursesFromSyllabus(BigInteger id);
}
