package capstone.project.credit_manager.web.exception;

import org.springframework.http.HttpStatus;

public interface CommonApiResponse {
    ErrorResponse getErrorResponse();

    String getMessage();

    HttpStatus getHttpStatus();
}
