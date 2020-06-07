
package gr.unipi.lecturer.model;


import javax.persistence.*;

@Entity
@Table(name = "authorities")
public class Authorities {
	@Id
	@Column(name = "authority")
	private String authority;
	
	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;
	
	public Student getUser() {
		return student;
	}
	public void setUser(Student user) {
		this.student = user;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
}

