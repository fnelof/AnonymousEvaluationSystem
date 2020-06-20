package gr.unipi.evaluate.model;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="courses")
public class Course {
	@Id
	@Column(name="id")
	private BigInteger id;
	
	@Column(name="title")
	private String title;

	@ManyToOne
	@JoinColumn(name="syllabus_id")
	private Syllabus syllabus;
	
	@Column(name="credits")
	private int credits;
	
	@Column(name="semester")
	private String semester;

	@Column(name = "attendance_prerequisite")
	private int attendancePrerequisite;

	@Column(name = "number_of_lectures")
	private int numberOfLectures;

	@OneToMany(mappedBy="course")
	Set<CourseStudent> courseStudents = new HashSet<>();
	
	@OneToMany(mappedBy="ticketStringValue")
    private Set<Ticket> tickets;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="courses_instructors",
				joinColumns = { @JoinColumn(name="course_id")},
				inverseJoinColumns  = {@JoinColumn(name="instructor_id")}
			)
	Set<Instructor> instructors = new HashSet<>();
	
	public Course(){}

	public Course(BigInteger id) {
		this.id = id;
	}

	public BigInteger getId() {
		return id;
	}


	public void setId(BigInteger id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public Syllabus getSyllabus() {
		return syllabus;
	}


	public void setSyllabus(Syllabus syllabus) {
		this.syllabus = syllabus;
	}


	public int getCredits() {
		return credits;
	}


	public void setCredits(int credits) {
		this.credits = credits;
	}


	public String getSemester() {
		return semester;
	}


	public void setSemester(String semester) {
		this.semester = semester;
	}

	public int getNumberOfLectures() {	return numberOfLectures; }

	public void setNumberOfLectures(int numberOfLectures) {	this.numberOfLectures = numberOfLectures; }

	public Set<CourseStudent> getCourseStudents() {
		return courseStudents;
	}


	public void setCourseStudents(Set<CourseStudent> courseStudents) {
		this.courseStudents = courseStudents;
	}


	public Set<Instructor> getInstructors() {
		return instructors;
	}


	public void setInstructors(Set<Instructor> instructors) {
		this.instructors = instructors;
	}

	public int getAttendancePrerequisite() { return attendancePrerequisite;	}

	@Override
	public String toString() {
		return "Course{" +
				"id=" + id +
				", title='" + title + '\'' +
				", syllabus=" + syllabus +
				", semester='" + semester + '\'' +
				", instructors=" + instructors +
				'}';
	}

}
