package capstone.project.credit_manager.domain.accounts;

import capstone.project.credit_manager.domain.Department;
import capstone.project.credit_manager.domain.StudentStatus;
import capstone.project.credit_manager.web.exception.ErrorResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@DiscriminatorValue("S")
public class Student extends Account {

    @ManyToOne(fetch = FetchType.LAZY)
    private Department multiMajor;

    @ManyToOne(fetch = FetchType.LAZY)
    private Department subMajor;

    @ManyToOne(fetch = FetchType.LAZY)
    private Department beforeMajor;

    private boolean isTransfer;

    private boolean isTransferDepartment;

    @Enumerated(EnumType.STRING)
    private StudentStatus studentStatus;

    @Builder
    public Student(String accountId, String password, Department major, Role role) {
        super(accountId, password, major, role);

        if (super.getRole() != role) {
            throw new FailedCreateAccountException(new ErrorResponse("NOT_MATCH_ROLE", "학생은 관리자 계정으로 가입할 수 없습니다."));
        }
        // TODO: 2020/06/11 위의 필드 생성자, add함수 추가
    }
}
