package gr.unipi.lecturer.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="departments")
public class Department {
	@Id
	@Column(name="name")
	private String name;
	
	@Column(name="building")
	private String building;

	@OneToMany(fetch=FetchType.LAZY,targetEntity=Syllabus.class,cascade = CascadeType.ALL,mappedBy="department")
	private List<Syllabus> syllabusList;
	
	@OneToMany(fetch=FetchType.LAZY,targetEntity=Instructor.class,cascade = CascadeType.ALL,mappedBy="department")
	private List<Instructor> instructorList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public List<Syllabus> getSyllabusList() {
		return syllabusList;
	}

	public void setSyllabusList(List<Syllabus> syllabusList) {
		this.syllabusList = syllabusList;
	}

	public List<Instructor> getInstructorList() {
		return instructorList;
	}

	public void setInstructorList(List<Instructor> instructorList) {
		this.instructorList = instructorList;
	}
}
