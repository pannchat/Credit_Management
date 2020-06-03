package capstone.project.credit_manager.domain;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype")
public abstract class Account {
    @Id @GeneratedValue
    private Long id;

    //todo 도메인에서 학번 유효성 검증을 할것인가 -> 그러면 조교회원이 걸림
    private String accountId;

    private String password;

    @OneToOne(fetch = FetchType.LAZY)
    private Department major;
}
