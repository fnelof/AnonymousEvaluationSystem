package gr.unipi.evaluate.model;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="syllabus")
public class Syllabus {
	@Id
	@Column(name="id")
	private BigInteger id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="duration")
	private int duration;
	
	@ManyToOne
	@JoinColumn(name="department")
	private Department department;
	
	@OneToMany(targetEntity=Student.class,cascade = CascadeType.ALL,mappedBy = "syllabus")
	private List<Student> studentList;
	
	@OneToMany(targetEntity=Course.class,cascade = CascadeType.ALL,mappedBy = "syllabus")
	private List<Course> courseList;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}
	
}
