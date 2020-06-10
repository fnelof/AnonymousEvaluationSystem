package gr.unipi.lecturer.controller;

import gr.unipi.lecturer.model.Course;
import gr.unipi.lecturer.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.Optional;

@RestController
public class CourseController {

    private CourseRepository courseRepository;

    @GetMapping("/getCourseTotalLectures")
    public ResponseEntity<Object> getCourseTotalLectures(@RequestParam BigInteger courseId){
        Optional<Course> course = courseRepository.findById(courseId);
        if(course.isPresent()){
            return ResponseEntity
                    .ok(course.get().getNumberOfLectures());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error! Could not find course with id " + courseId);
    }


    @Autowired
    public void setCourseRepository(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
}
