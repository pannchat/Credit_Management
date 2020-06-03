package capstone.project.credit_manager.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class CourseSemester {
    private int year;
    @Enumerated(EnumType.STRING)
    private Semester semester;

    public CourseSemester(int year, Semester semester) {
        this.year = year;
        this.semester = semester;
    }
}
