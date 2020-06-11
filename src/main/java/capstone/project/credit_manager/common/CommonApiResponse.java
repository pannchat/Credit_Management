package capstone.project.credit_manager.common;

import org.springframework.http.HttpStatus;

public interface CommonApiResponse {
    ErrorResponse getErrorResponse();

    String getMessage();

    HttpStatus getHttpStatus();
}
