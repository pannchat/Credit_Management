package capstone.project.credit_manager.common;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ErrorResponse {

    private String code;
    private String msg;

    public ErrorResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}