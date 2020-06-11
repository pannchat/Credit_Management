package capstone.project.credit_manager.config.auth;

import capstone.project.credit_manager.web.exception.CommonApiResponse;
import capstone.project.credit_manager.web.exception.ErrorResponse;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidTokenException extends RuntimeException implements CommonApiResponse {
    private static final String DEFAULT_ERROR_MESSAGE = "유효하지 않은 인증토큰 입니다.";
    private static final String CODE = "UNAUTHORIZED";

    private ErrorResponse errorResponse;
    private HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

    public InvalidTokenException() {
        super(DEFAULT_ERROR_MESSAGE);
        this.errorResponse = new ErrorResponse(CODE, DEFAULT_ERROR_MESSAGE);
    }
}
