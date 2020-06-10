package gr.unipi.lecturer.repository;

import gr.unipi.lecturer.model.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface CourseRepository extends CrudRepository<Course, BigInteger> {
}
