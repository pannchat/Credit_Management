package capstone.project.credit_manager.Domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Student {
    @Id @GeneratedValue
    @Column(name = "student_id")
    Long id;

    // 로그인 정보
    Integer sNum;
    String password;

    // 학생 정보
    String name;
    String major;
    Float grade;
    Integer admissionYear;

    // 특수 상태 여부
    Boolean isTransferUni;
    Boolean isTransferMajor;
    Boolean isDouble;

}
