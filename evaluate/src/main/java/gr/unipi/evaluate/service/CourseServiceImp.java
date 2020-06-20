package gr.unipi.evaluate.service;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

import gr.unipi.evaluate.common.Constants;
import gr.unipi.evaluate.dao.PublicKeyDetailsDao;
import gr.unipi.evaluate.model.PublicKeyDetails;
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

	@Autowired
	PublicKeyDetailsDao publicKeyDetailsDao;
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

	@Transactional
	public boolean isEligible(List<BigInteger> ticketChain, String initialTicket, BigInteger courseId, BigInteger instructorId) throws IOException, CertificateException, KeyStoreException, NoSuchAlgorithmException {
		Course course = courseDao.findCourseById(courseId);

		PublicKeyDetails publicKeyDetails =  publicKeyDetailsDao.getPublicKeyDetailsOfCourseInstructor(courseId, instructorId);

		if(validateTicketChain(publicKeyDetails, ticketChain, initialTicket, course))
			return true;
		return false;
	}

	private boolean validateTicketChain(PublicKeyDetails publicKeyDetails, List<BigInteger> ticketChain, String initialTicket, Course course) throws NoSuchAlgorithmException {
		List<String> hashes = createOriginalHashChain(initialTicket, course);
		if(hashes.size() != ticketChain.size())
			return false;
		int validSignatures = 0;
		for(int i=0; i<ticketChain.size();i++){
			if(isSignedTicketValid(publicKeyDetails, ticketChain.get(i), hashes.get(i)))
				validSignatures++;
			else if(!isValidHash(ticketChain.get(i), hashes.get(i)))
				return false;
		}

		return validSignatures >= course.getAttendancePrerequisite();

	}

	private boolean isValidHash(BigInteger ticket, String hash) {
		BigInteger hashBigInteger = new BigInteger(hash,16);

		return hashBigInteger.equals(ticket);

	}

	private boolean isSignedTicketValid(PublicKeyDetails publicKeyDetails, BigInteger signed, String original) {
		BigInteger originalBigInteger = new BigInteger(original,16);

		return signed.modPow(publicKeyDetails.getExponent(),publicKeyDetails.getModulus()).equals(originalBigInteger);
	}

	private List<String> createOriginalHashChain(String initialTicket, Course course) throws NoSuchAlgorithmException {

		List<String> result = new ArrayList<>();
		int lectures = course.getNumberOfLectures();

		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		String t = initialTicket;
		for(int i=0;i<lectures;i++){
			byte[] encodedhash = digest.digest(
					t.getBytes(StandardCharsets.UTF_8));

			result.add(bytesToHex(encodedhash));
			t = result.get(i);
		}
		return result;
	}

	private static String bytesToHex(byte[] hash) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if(hex.length() == 1) hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}

}
