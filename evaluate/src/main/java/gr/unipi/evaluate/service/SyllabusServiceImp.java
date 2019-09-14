package gr.unipi.evaluate.service;

import java.util.ArrayList;
import java.util.List;

import gr.unipi.evaluate.common.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gr.unipi.evaluate.dao.SyllabusDao;
import gr.unipi.evaluate.model.Syllabus;

@Service
public class SyllabusServiceImp implements SyllabusService{

	private static final Logger logger = LogManager.getLogger(SyllabusServiceImp.class);

	@Autowired
	SyllabusDao syllabusDao;
	
	/* 
	 * Gets the department's syllabus list and 
	 * creates the json object for the front end
	*/
	@Transactional
	public JSONObject getSyllabusList(String department) {
		logger.info("Start getSyllabusList, department: {}", department);
		List<Syllabus> syllabusList = new ArrayList<Syllabus>();
		syllabusList = syllabusDao.getSyllabusList(department);
		JSONArray jsonArray = new JSONArray();

		for(Syllabus s: syllabusList) {
			JSONObject syllabus = new JSONObject();
			syllabus.put(Constants.NAME, s.getName());
			syllabus.put(Constants.ID,s.getId());
			jsonArray.put(syllabus);
		}
		JSONObject response = new JSONObject();
		response.put(Constants.SYLLABUS_LIST,jsonArray);

		logger.info("End getSyllabusList, department: {}", department);
		return response;
	}

}
