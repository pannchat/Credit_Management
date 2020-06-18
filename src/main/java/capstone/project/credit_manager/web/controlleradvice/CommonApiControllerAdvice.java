package capstone.project.credit_manager.web.controlleradvice;

import capstone.project.credit_manager.web.exception.CommonApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CommonApiControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity paymentProcessException(CommonApiResponse e) {
        log.error("{} {}", e.getHttpStatus(), e.getMessage());

        return ResponseEntity.status(e.getHttpStatus()).body(e.getErrorResponse());
    }
}