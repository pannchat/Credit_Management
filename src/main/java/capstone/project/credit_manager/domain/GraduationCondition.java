package capstone.project.credit_manager.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class GraduationCondition {
    @Id
    @GeneratedValue
    private Long id;

    private int year;   // todo: year value로 뺄까..

    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;

    @Embedded
    private Credit credit;  // todo 그냥 row 한 줄로 둘까.. Department 중복 장난 아닐듯..
}
