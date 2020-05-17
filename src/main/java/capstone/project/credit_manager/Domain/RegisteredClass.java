package capstone.project.credit_manager.Domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class RegisteredClass {
    @Id @GeneratedValue
    Long id;

    /* 수업 정보 */
    Integer year;       // 개설년도
    String semester;    // 개설학기
    String className;   // 수업명
    String classNum;    // 과목코드
    Integer credit;     // 학점

}
