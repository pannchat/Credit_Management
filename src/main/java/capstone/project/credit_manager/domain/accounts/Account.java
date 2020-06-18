package capstone.project.credit_manager.domain.accounts;

import capstone.project.credit_manager.domain.Department;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype")
public abstract class Account {
    @Id
    @GeneratedValue
    protected Long id;

    @Column(length = 9, unique = true, nullable = false)
    protected String accountId;

    @Column(length = 100, nullable = false)
    protected String password;

    @Column(length = 20, nullable = false)
    protected String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_id", nullable = false)
    protected Department major;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected Role role;

    protected Account(String accountId, String password, String name, Department major, Role role) {
        this.accountId = accountId;
        this.password = password;
        this.name = name;
        this.major = major;
        this.role = role;
    }

    public boolean isMatchPassword(PasswordEncoder passwordEncoder, String password) {
        return passwordEncoder.matches(password, this.password);
    }

    public void update(Account account) {
        this.accountId = account.accountId;
        this.password = account.password;
        this.name = account.name;
        this.major = account.major;
    }
}

