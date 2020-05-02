package gr.unipi.issue.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigInteger;

@Embeddable
public class CourseStudentPK implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name="course_id")
    private BigInteger courseId;

    @Column(name="student_id")
    private BigInteger studentId;

    public BigInteger getCourseId() {
        return courseId;
    }

    public void setCourseId(BigInteger courseId) {
        this.courseId = courseId;
    }

    public BigInteger getStudentId() {
        return studentId;
    }

    public void setStudentId(BigInteger studentId) {
        this.studentId = studentId;
    }
}
