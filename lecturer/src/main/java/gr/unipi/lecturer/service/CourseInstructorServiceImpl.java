package gr.unipi.lecturer.service;

import gr.unipi.lecturer.dto.CourseInstructorDTO;
import gr.unipi.lecturer.model.CourseInstructor;
import gr.unipi.lecturer.repository.CourseInstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseInstructorServiceImpl implements CourseInstructorService {

    @Autowired
    CourseInstructorRepository courseInstructorRepository;


    @Transactional
    public List<CourseInstructorDTO> courseInstructorPagination(String courseName, String instructorName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CourseInstructor> courseInstructorList = courseInstructorRepository.findAll(pageable);
        return createCourseInstructorDTO(courseInstructorList.getContent());
    }

    private List<CourseInstructorDTO> createCourseInstructorDTO(List<CourseInstructor> courseInstructorList) {
        List result = new ArrayList();
        for(CourseInstructor courseInstructor: courseInstructorList){
          CourseInstructorDTO courseInstructorDTO = new CourseInstructorDTO();
          courseInstructorDTO.setCourseId(courseInstructor.getId().getCourseId());
          courseInstructorDTO.setInstructorId(courseInstructor.getId().getInstructorId());
          courseInstructorDTO.setCourseTitle(courseInstructor.getCourse().getTitle());
          courseInstructorDTO.setSyllabus(courseInstructor.getCourse().getSyllabus().getName());
          courseInstructorDTO.setDepartment(courseInstructor.getInstructor().getDepartment().getName());
          courseInstructorDTO.setInstructorFirstName(courseInstructor.getInstructor().getFirstname());
          courseInstructorDTO.setInstructorLastName(courseInstructor.getInstructor().getLastname());
          courseInstructorDTO.setInstructorTitle(courseInstructor.getInstructor().getTitle());

          result.add(courseInstructorDTO);
        }
        return result;
    }
}
