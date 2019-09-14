package gr.unipi.issue.service;

import java.math.BigInteger;
import java.util.List;

import gr.unipi.issue.common.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gr.unipi.issue.dao.CourseDao;
import gr.unipi.issue.dao.StudentDao;
import gr.unipi.issue.model.Course;
import gr.unipi.issue.model.Instructor;

@Service
public class CourseServiceImp implements CourseService {

	private static final Logger logger = LogManager.getLogger(CourseServiceImp.class);

	@Autowired
	StudentDao studentDao;
	@Autowired
	CourseDao courseDao;
	
	// Gets the Student's courses which he has not requested for a ticket before
	@Transactional
	public JSONObject getCourses() {
		logger.info("Start getCourses");
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		BigInteger uid = studentDao.getUidFromUsername(username);
		
		// Gets non issued courses from db
		List<Course> courseList = courseDao.getNonIssuedCoursesFromUid(uid);
		
		
		JSONObject response = new JSONObject();
		JSONArray courses = new JSONArray();
		// Populates json object with each course's details
		for (Course c: courseList) {
			JSONObject course = new JSONObject();
			
			course.put(Constants.ID, c.getId());
			course.put(Constants.TITLE, c.getTitle());
			
			JSONArray instructorList = new JSONArray();
			// Populates each course's json object with the corresponding instructors
			for(Instructor i : c.getInstructors()) {
				JSONObject instructor = new JSONObject();
				instructor.put(Constants.ID, i.getId());
				instructor.put(Constants.NAME,i.getFirstname() + " " + i.getLastname());
				instructor.put(Constants.TITLE, i.getTitle());
				
				instructorList.put(instructor);
			}
			course.put(Constants.INSTRUCTOR_LIST, instructorList);
			courses.put(course);
		}
		
		response.put(Constants.COURSE_LIST, courses);
		logger.info("End getCourses, username: {}",username);
		return response;
	}

}
