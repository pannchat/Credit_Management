package capstone.project.credit_manager.Domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class MyCredit {
    @Id
    @GeneratedValue
    Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "student_id")
    Student student;

    /* 커리큘럼 */
}
