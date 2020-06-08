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

    public CourseInstructorPK getId() {
        return id;
    }

    public void setId(CourseInstructorPK id) {
        this.id = id;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
