
package gr.unipi.evaluate.model;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="students")
public class Student {
	@Id
	@Column(name="id")
	private BigInteger id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name="firstname")
	private String firstname;
	
	@Column(name="lastname")
	private String lastname;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "issued", nullable = false)
	private boolean issued;
	
	@Column(name = "enabled", nullable = false)
	private boolean enabled;

	@Column(name="login_attempts",nullable = false)
	private int loginAttempts;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
	private Set<Authorities> authorities = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name="syllabus_id")
	private Syllabus syllabus;

	@OneToMany(mappedBy="student")
	private Set<CourseStudent> courseStudents = new HashSet<>();

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isIssued() {
		return issued;
	}

	public void setIssued(boolean issued) {
		this.issued = issued;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public int getLoginAttempts() {
		return loginAttempts;
	}

	public void setLoginAttempts(int loginAttempts) {
		this.loginAttempts = loginAttempts;
	}

	public Set<Authorities> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authorities> authorities) {
		this.authorities = authorities;
	}

	public Syllabus getSyllabus() {
		return syllabus;
	}

	public void setSyllabus(Syllabus syllabus) {
		this.syllabus = syllabus;
	}

	public Set<CourseStudent> getCourseStudents() {
		return courseStudents;
	}

	public void setCourseStudents(Set<CourseStudent> courseStudents) {
		this.courseStudents = courseStudents;
	}
	

}

