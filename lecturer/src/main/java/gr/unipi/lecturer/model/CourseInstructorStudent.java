package gr.unipi.lecturer.model;


import javax.persistence.*;

@Entity
@Table(name="courses_instructors_students")
public class CourseInstructorStudent  {

	@EmbeddedId
	private CourseInstructorStudentPK id;
	
	@ManyToOne
	@MapsId("courseId")
	@JoinColumn(name = "course_id")
	private Course course;
	
	@ManyToOne
	@MapsId("instructorId")
	@JoinColumn(name = "instructor_id")
	private Instructor instructor;

	@ManyToOne
	@MapsId("studentId")
	@JoinColumn(name="student_id")
	private Student student;

	public CourseInstructorStudentPK getId() {
		return id;
	}

	public void setId(CourseInstructorStudentPK id) {
		this.id = id;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
}
