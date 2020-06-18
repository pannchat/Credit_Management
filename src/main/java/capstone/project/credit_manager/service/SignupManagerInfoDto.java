package capstone.project.credit_manager.service;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SignupManagerInfoDto {
    protected String accountId;
    protected String username;
    protected String password;
    protected Long departmentId;

    @Builder
    public SignupManagerInfoDto(String accountId, String username, String password, Long departmentId) {
        this.accountId = accountId;
        this.username = username;
        this.password = password;
        this.departmentId = departmentId;
    }
}
