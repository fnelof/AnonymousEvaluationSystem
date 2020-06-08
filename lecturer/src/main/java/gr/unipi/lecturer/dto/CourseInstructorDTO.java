package gr.unipi.lecturer.dto;

import java.io.Serializable;
import java.math.BigInteger;

public class CourseInstructorDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private BigInteger courseId;
    private BigInteger instructorId;
    private String courseTitle;
    private String syllabus;
    private String department;
    private String instructorFirstName;
    private String instructorLastName;
    private String instructorTitle;

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

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseName) {
        this.courseTitle = courseName;
    }

    public String getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getInstructorFirstName() {
        return instructorFirstName;
    }

    public void setInstructorFirstName(String instructorFirstName) {
        this.instructorFirstName = instructorFirstName;
    }

    public String getInstructorLastName() {
        return instructorLastName;
    }

    public void setInstructorLastName(String instructorLastName) {
        this.instructorLastName = instructorLastName;
    }

    public String getInstructorTitle() {
        return instructorTitle;
    }

    public void setInstructorTitle(String instructorTitle) {
        this.instructorTitle = instructorTitle;
    }
}
