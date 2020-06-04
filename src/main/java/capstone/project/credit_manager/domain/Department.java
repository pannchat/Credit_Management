package capstone.project.credit_manager.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Department {
    @Id @GeneratedValue
    private Long id;

    private String name;

    private String link;

    @ManyToOne(fetch = FetchType.LAZY)
    private Department category;
}
