package capstone.project.credit_manager.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Class")
public class Subject {
    @Id
    @GeneratedValue
    private Long id;

    private Long subjectNum;    //과목번호
}
