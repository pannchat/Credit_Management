package capstone.project.credit_manager.domain;

import lombok.Getter;

@Getter
public enum Grade {
    APLUS(4.5),
    A0(4.0),
    BPLUS(3.5),
    B0(3.0),
    CPLUS(2.5),
    C0(2.0),
    DPLUS(1.5),
    D0(1.0),
    F(0.0);

    private double grade;

    Grade(double grade) {
        this.grade = grade;
    }
}
