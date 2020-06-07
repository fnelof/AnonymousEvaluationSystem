package gr.unipi.lecturer.model;

import javax.persistence.*;

@Entity
@Table(name="courses_students")
public class CourseStudent {
    @EmbeddedId
    private CourseStudentPK id;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "attendance")
    private int attendance;

}
