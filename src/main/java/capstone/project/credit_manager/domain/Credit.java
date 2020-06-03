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
public class Credit {
    @Enumerated(EnumType.STRING)
    private SubjectType type;

    private double credit;

    public Credit(SubjectType type, double credit) {
        this.type = type;
        this.credit = credit;
    }
}
