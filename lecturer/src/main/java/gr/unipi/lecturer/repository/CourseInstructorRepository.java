package gr.unipi.lecturer.repository;

import gr.unipi.lecturer.model.CourseInstructor;
import gr.unipi.lecturer.model.CourseInstructorPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseInstructorRepository extends PagingAndSortingRepository<CourseInstructor,Long> {

}
