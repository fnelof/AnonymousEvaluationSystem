package gr.unipi.evaluate.service;

import java.util.List;

import gr.unipi.evaluate.common.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gr.unipi.evaluate.dao.DepartmentDao;
import gr.unipi.evaluate.model.Department;

@Service
public class DepartmentServiceImp implements DepartmentService{

	private static final Logger logger = LogManager.getLogger(DepartmentServiceImp.class);

	@Autowired
	DepartmentDao departmentDao;
	
	// Creates the list of the departments for the front end in json object format
	@Transactional
	public JSONObject getDepartmentList() {
		logger.info("Start getDepartmentList");
		JSONObject response = new JSONObject();
		List<Department> departmentList = departmentDao.getDepartmentList();
		JSONArray deptList = new JSONArray();
		for(Department dept: departmentList) {
			JSONObject department = new JSONObject();
			department.put(Constants.NAME, dept.getName());
			deptList.put(department);
		}		
		response.put(Constants.DEPARTMENT_LIST, deptList);
		logger.info("End getDepartmentList");

		return response;
	}

}
