package capstone.project.credit_manager.service.exception;

import capstone.project.credit_manager.web.exception.CommonApiResponse;
import capstone.project.credit_manager.web.exception.ErrorResponse;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotFoundDataException extends IllegalArgumentException implements CommonApiResponse {
    private static final String DEFAULT_ERROR_MESSAGE = "해당 데이터를 찾을 수 없습니다.";
    private static final String CODE = "NOT_FOUND";

    private ErrorResponse errorResponse;
    private HttpStatus httpStatus = HttpStatus.NOT_FOUND;

    public NotFoundDataException(ErrorResponse errorResponse) {
        super(errorResponse.getMsg());
        this.errorResponse = errorResponse;
    }
}
