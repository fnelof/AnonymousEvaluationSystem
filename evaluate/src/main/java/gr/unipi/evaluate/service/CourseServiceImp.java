package gr.unipi.evaluate.service;

import java.math.BigInteger;
import java.util.List;

import gr.unipi.evaluate.common.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

	private static final Logger logger = LogManager.getLogger(CourseServiceImp.class);

	@Autowired
	CourseDao courseDao;
	
	/* 
	 * Gets the courses and their instructors from the db
	 * and creates the JSONObject response for the user
	*/
	@Transactional
	public JSONObject getCoursesFromSyllabus(BigInteger id) {
		logger.info("Start getCoursesFromSyllabus with syllabusId: {}", id );
		List<Course> courseList =  courseDao.getCoursesFromSyllabus(id);
		JSONObject response = new JSONObject();
		JSONArray courses = new JSONArray();
		for(Course c:courseList) {
			
			// Creates the json object of the course
			JSONObject course = new JSONObject();
			course.put(Constants.ID, c.getId());
			course.put(Constants.TITLE, c.getTitle());
			JSONArray instructors = new JSONArray();
			// fills the list of the instructors of the course
			for(Instructor instructor: c.getInstructors()) {
				JSONObject inst = new JSONObject();
				inst.put(Constants.ID, instructor.getId());
				inst.put(Constants.TITLE, instructor.getTitle());
				inst.put(Constants.NAME, instructor.getFirstname() +" " + instructor.getLastname());
				instructors.put(inst);				
			}
			course.put(Constants.INSTRUCTOR_LIST, instructors);
			courses.put(course);
		}
		response.put(Constants.COURSE_LIST, courses);

		logger.info("End getCoursesFromSyllabus with syllabusId: {}", id );

		return response;
	}
	
}
