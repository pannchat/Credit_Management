package capstone.project.credit_manager.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * subject 에 두어야 할 필드를 굳이굳이 여기 새로판 이유
 * 학점, 과목분류가 바꼈을때 바뀐 이후의 학생들만 적용해야하기 때문
 * 이름도 바뀔 수 있음(전에 들었던 학생들의 과목명 혼선을 방지)
 * 강의실, 수업시간 맨날 바뀜
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class SemesterSubject {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Subject subject;

    @Embedded
    private CourseSemester courseSemester;

    private String name;

    private int division;   //분반

    private int countOfStudent;

    private String location;

    //todo String.. 좀더고민 (enum으로 빼기엔 너무 많음..)
    private String subjectDate;

    @Embedded
    private Credit credit;

    // todo: 수강대상 학과가 여러개라면..
    @OneToOne(fetch = FetchType.LAZY)
    private Department department;


}
