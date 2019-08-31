package gr.unipi.evaluate.service;

import java.util.ArrayList;
import java.util.List;

import gr.unipi.evaluate.common.Constants;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gr.unipi.evaluate.dao.DepartmentDao;
import gr.unipi.evaluate.model.Department;

@Service
public class DepartmentServiceImp implements DepartmentService{
	@Autowired
	DepartmentDao departmentDao;
	
	// Creates the list of the departments for the front end in json object format
	@Transactional
	public JSONObject getDepartmentList() {
		
		JSONObject response = new JSONObject();
		List<Department> departmentList = new ArrayList<Department>();
		
		departmentList = departmentDao.getDepartmentList();
		JSONArray deptList = new JSONArray();
		for(Department dept: departmentList) {
			JSONObject department = new JSONObject();
			department.put(Constants.NAME, dept.getName());
			deptList.put(department);
		}		
		response.put(Constants.DEPARTMENT_LIST, deptList);
		return response;
	}

}
