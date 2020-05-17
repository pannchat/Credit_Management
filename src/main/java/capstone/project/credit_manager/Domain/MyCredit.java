package capstone.project.credit_manager.Domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import static javax.persistence.FetchType.LAZY;

public class MyCredit {
    @Id
    @GeneratedValue
    Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "student_id")
    Student student;

    Float grade;

    /* 커리큘럼 */
}
