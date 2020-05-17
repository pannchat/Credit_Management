package capstone.project.credit_manager.Domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Manager {
    @Id @GeneratedValue
    Long id;

    Integer mNum;
    String Pw;
    String major;
}
