package capstone.project.credit_manager.Domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class GraduationRequirement {
    @Id @GeneratedValue
    Long id;

}
