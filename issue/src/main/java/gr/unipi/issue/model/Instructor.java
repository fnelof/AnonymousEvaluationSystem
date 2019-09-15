package gr.unipi.issue.model;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="instructors")
public class Instructor {
	@Id
	@Column(name="id")
	private BigInteger id;
	
	@Column(name="firstname")
	private String firstname;
	
	@Column(name="lastname")
	private String lastname;
	
	@Column(name="title")
	private String title;
	
	@Column(name="phone")
	private String phone;
	
	@ManyToOne
	@JoinColumn(name="department")
	private Department department;
	
	@ManyToMany(mappedBy="instructors")
	private Set<Course> courses = new HashSet<>();

	@OneToMany(mappedBy = "instructor")
    private Set<CourseInstructorStudent> courseInstructorStudent = new HashSet<>();

	public Set<CourseInstructorStudent> getCourseInstructorStudent() {
		return courseInstructorStudent;
	}

	public void setCourseInstructorStudent(Set<CourseInstructorStudent> courseInstructorStudent) {
		this.courseInstructorStudent = courseInstructorStudent;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}


}
