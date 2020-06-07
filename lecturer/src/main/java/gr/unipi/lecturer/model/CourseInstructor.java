package gr.unipi.lecturer.model;

import javax.persistence.*;

@Entity
@Table(name="courses_instructors")
public class CourseInstructor {
    @EmbeddedId
    private CourseInstructorPK id;

    @ManyToOne
    @MapsId("instructorId")
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

}
