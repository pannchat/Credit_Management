package capstone.project.credit_manager.service;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginInfoRequestDto {
    private String accountId;
    private String password;
}
