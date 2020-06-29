package capstone.project.credit_manager.repository.account.dto;

import capstone.project.credit_manager.domain.accounts.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommonAccountInfoDto {
    private Long id;
    private String accountId;
    private String accountName;
    private Long departmentId;
    private String departmentName;
    private Role accountRole;

    @Builder
    public CommonAccountInfoDto(Long id, String accountId, String accountName, Long departmentId, String departmentName, Role accountRole) {
        this.id = id;
        this.accountId = accountId;
        this.accountName = accountName;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.accountRole = accountRole;
    }
}
