package capstone.project.credit_manager.service;

public class FailedAuthenticationException extends RuntimeException {
    public FailedAuthenticationException() {
        super("로그인에 실패하였습니다.");
    }
}
