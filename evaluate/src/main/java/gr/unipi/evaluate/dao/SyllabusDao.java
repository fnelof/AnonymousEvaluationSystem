package gr.unipi.evaluate.dao;

import java.util.List;

import gr.unipi.evaluate.model.Syllabus;

public interface SyllabusDao {
	public List<Syllabus> getSyllabusList(String department);
}
