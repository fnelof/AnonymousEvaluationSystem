package gr.unipi.issue.dao;

import gr.unipi.issue.model.Student;

public interface UserDetailsDao {
	Student findUserByUsername(String username);
}
