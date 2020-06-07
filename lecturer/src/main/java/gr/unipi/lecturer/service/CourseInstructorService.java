package gr.unipi.lecturer.service;

import gr.unipi.lecturer.model.CourseInstructor;

import java.util.List;

public interface CourseInstructorService {
    List<CourseInstructor> courseInstructorPagination(String courseName, String instructorName, int p1, int p2);
}
