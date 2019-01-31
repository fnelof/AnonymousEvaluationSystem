package gr.unipi.evaluate.service;

import java.math.BigInteger;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gr.unipi.evaluate.dao.CourseDao;
import gr.unipi.evaluate.model.Course;
import gr.unipi.evaluate.model.Instructor;

@Service
public class CourseServiceImp implements CourseService {
	@Autowired
	CourseDao courseDao;
	
	/* 
	 * Gets the courses and their instructors from the db
	 * and creates the JSONObject response for the user
	*/
	@Transactional
	public JSONObject getCoursesFromSyllabus(BigInteger id) {

		List<Course> courseList =  courseDao.getCoursesFromSyllabus(id);
		JSONObject response = new JSONObject();
		JSONArray courses = new JSONArray();
		for(Course c:courseList) {
			
			// Creates the json object of the course
			JSONObject course = new JSONObject();
			course.put("id", c.getId());
			course.put("title", c.getTitle());
			JSONArray instructors = new JSONArray();
			// fills the list of the instructors of the course
			for(Instructor instructor: c.getInstructors()) {
				JSONObject inst = new JSONObject();
				inst.put("id", instructor.getId());
				inst.put("title", instructor.getTitle());
				inst.put("name", instructor.getFirstname() +" " + instructor.getLastname());
				instructors.put(inst);				
			}
			course.put("instructorList", instructors);
			courses.put(course);
		}
		response.put("courseList", courses);
		
		return response;
	}
	
}
