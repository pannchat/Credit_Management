package capstone.project.credit_manager.repository.account.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommonAccountInfoDto {
    private Long id;
    private String accountId;
    private String username;
    private String departmentName;

    @Builder
    public CommonAccountInfoDto(Long id, String accountId, String username, String departmentName) {
        this.id = id;
        this.accountId = accountId;
        this.username = username;
        this.departmentName = departmentName;
    }
}
