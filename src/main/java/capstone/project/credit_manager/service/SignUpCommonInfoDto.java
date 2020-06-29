package capstone.project.credit_manager.service;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SignUpCommonInfoDto {
    private String accountId;
    private String accountName;
    private String password;
    private Long departmentId;

    @Builder
    public SignUpCommonInfoDto(String accountId, String accountName, String password, Long departmentId) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.password = password;
        this.departmentId = departmentId;
    }
}
