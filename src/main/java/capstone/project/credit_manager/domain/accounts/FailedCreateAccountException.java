package capstone.project.credit_manager.domain.accounts;

import capstone.project.credit_manager.web.exception.CommonApiResponse;
import capstone.project.credit_manager.web.exception.ErrorResponse;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class FailedCreateAccountException extends RuntimeException implements CommonApiResponse {
    private ErrorResponse errorResponse;
    private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public FailedCreateAccountException(ErrorResponse errorResponse) {
        super(errorResponse.getMsg());
    }
}
