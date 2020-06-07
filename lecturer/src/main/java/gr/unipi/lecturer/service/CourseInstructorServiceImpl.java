package gr.unipi.lecturer.service;

import gr.unipi.lecturer.model.CourseInstructor;
import gr.unipi.lecturer.repository.CourseInstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseInstructorServiceImpl implements CourseInstructorService {

    @Autowired
    CourseInstructorRepository courseInstructorRepository;


    @Transactional
    public List<CourseInstructor> courseInstructorPagination(String courseName, String instructorName, int p1, int p2) {
        List<CourseInstructor> courseInstructorList = (List<CourseInstructor>) courseInstructorRepository.findAll();
        return courseInstructorList;
    }
}
