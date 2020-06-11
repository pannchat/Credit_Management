package capstone.project.credit_manager.domain.accounts;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Arrays;

@Getter
public class LoggedInAccount extends User {
    private Long id;
    private Long majorId;
    private String majorName;

    public LoggedInAccount(Account account) {
        super(account.getAccountId(), account.getPassword(),
                Arrays.asList(new SimpleGrantedAuthority("ROLE_" + account.getRole())));
        this.id = account.getId();
        this.majorId = account.getMajor().getId();
        this.majorName = account.getMajor().getName();
    }
}
