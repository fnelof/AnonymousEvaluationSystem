package gr.unipi.evaluate.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gr.unipi.evaluate.dao.SyllabusDao;
import gr.unipi.evaluate.model.Syllabus;

@Service
public class SyllabusServiceImp implements SyllabusService{
	@Autowired
	SyllabusDao syllabusDao;
	
	/* 
	 * Gets the department's syllabus list and 
	 * creates the json object for the front end
	*/
	@Transactional
	public JSONObject getSyllabusList(String department) {

		List<Syllabus> syllabusList = new ArrayList<Syllabus>();
		syllabusList = syllabusDao.getSyllabusList(department);
		JSONArray jsonArray = new JSONArray();

		for(Syllabus s: syllabusList) {
			JSONObject syllabus = new JSONObject();
			syllabus.put("name", s.getName());
			syllabus.put("id",s.getId());
			jsonArray.put(syllabus);
		}
		JSONObject response = new JSONObject();
		response.put("syllabusList",jsonArray);
		return response;
	}

}
