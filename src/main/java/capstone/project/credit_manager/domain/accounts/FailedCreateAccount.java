package capstone.project.credit_manager.domain.accounts;

import capstone.project.credit_manager.common.CommonApiResponse;
import capstone.project.credit_manager.common.ErrorResponse;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class FailedCreateAccount extends RuntimeException implements CommonApiResponse {
    private ErrorResponse errorResponse;
    private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public FailedCreateAccount(ErrorResponse errorResponse) {
        super(errorResponse.getMsg());
    }
}
