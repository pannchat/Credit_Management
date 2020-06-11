package capstone.project.credit_manager.domain;

import capstone.project.credit_manager.domain.accounts.Student;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 학생의 수업이력
 */

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class StudentSubject {

 @Id @GeneratedValue
 private Long id;

 @ManyToOne(fetch = FetchType.LAZY)
 private Student student;

 @ManyToOne(fetch = FetchType.LAZY)
 private Subject subject;

 @Enumerated(EnumType.STRING)
 private Grade grade;

 private boolean isRetake; //재수강여부

 @Embedded
 private CourseSemester courseSemester;

}
