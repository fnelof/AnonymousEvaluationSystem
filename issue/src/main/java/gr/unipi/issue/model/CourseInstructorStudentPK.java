package gr.unipi.issue.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CourseInstructorStudentPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name="course_id")
	private BigInteger courseId;
	
	@Column(name="instructor_id")
	private BigInteger instructorId;
	
	@Column(name="student_id")
	private BigInteger studentId;
	
	@Column(name="issued")
	private boolean issued;

	public BigInteger getCourseId() {
		return courseId;
	}

	public void setCourseId(BigInteger courseId) {
		this.courseId = courseId;
	}

	public BigInteger getInstructorId() {
		return instructorId;
	}

	public void setInstructorId(BigInteger instructorId) {
		this.instructorId = instructorId;
	}

	public BigInteger getStudentId() {
		return studentId;
	}

	public void setStudentId(BigInteger studentId) {
		this.studentId = studentId;
	}

	public boolean isIssued() {
		return issued;
	}

	public void setIssued(boolean issued) {
		this.issued = issued;
	}
}
