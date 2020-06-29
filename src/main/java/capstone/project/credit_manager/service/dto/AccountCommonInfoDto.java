package capstone.project.credit_manager.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountCommonInfoDto {
    private String accountId;
    private String accountName;
    private String password;
    private Long departmentId;

    @Builder
    public AccountCommonInfoDto(String accountId, String accountName, String password, Long departmentId) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.password = password;
        this.departmentId = departmentId;
    }
}
