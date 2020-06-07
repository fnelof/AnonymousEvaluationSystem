package gr.unipi.lecturer.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigInteger;

@Embeddable
public class CourseInstructorPK implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name="course_id")
    private BigInteger courseId;

    @Column(name="instructor_id")
    private BigInteger instructorId;

}
