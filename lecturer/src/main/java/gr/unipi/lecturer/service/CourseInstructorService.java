package gr.unipi.lecturer.service;

import gr.unipi.lecturer.dto.CourseInstructorDTO;

import java.util.List;

public interface CourseInstructorService {
    List<CourseInstructorDTO> courseInstructorPagination(String courseName, String instructorName, int p1, int p2);
}
