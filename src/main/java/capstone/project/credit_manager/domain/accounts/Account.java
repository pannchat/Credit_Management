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
    @Id @GeneratedValue
    private Long id;

    @Column(length = 9, unique = true, nullable = false)
    private String accountId;

    @Column(length = 100, nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_id", nullable = false)
    private Department major;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public Account(String accountId, String password, Department major, Role role) {
        this.accountId = accountId;
        this.password = password;
        this.major = major;
        this.role = role;
    }

    public boolean isMatchPassword(PasswordEncoder passwordEncoder, String password) {
        return passwordEncoder.matches(password, this.password);
    }
}

