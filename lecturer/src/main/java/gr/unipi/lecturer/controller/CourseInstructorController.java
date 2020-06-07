package gr.unipi.lecturer.controller;

import gr.unipi.lecturer.model.CourseInstructor;
import gr.unipi.lecturer.service.CourseInstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseInstructorController {

    @Autowired
    CourseInstructorService courseInstructorService;

    @GetMapping("/getCourseInstructorPagination")
    public String getCourseInstructorPagination(@RequestParam String courseName, @RequestParam String instructorName, @RequestParam int p1, @RequestParam int p2){
        List<CourseInstructor> courseInstructorList = courseInstructorService.courseInstructorPagination(courseName,instructorName,0,5);

        return "test";
    }
}