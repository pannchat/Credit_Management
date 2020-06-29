package capstone.project.credit_manager.service;

import capstone.project.credit_manager.domain.StudentStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpStudentInfoDto extends SignUpCommonInfoDto{
    private Long multiMajorId;
    private Long subMajorId;
    private Long beforeMajorId;
    private StudentStatus studentStatus;
    private boolean isTransfer = false;
    private boolean isTransferDepartment = false;

    @Builder
    public SignUpStudentInfoDto(String accountId, String accountName, String password, Long departmentId, Long multiMajorId, Long subMajorId, Long beforeMajorId, StudentStatus studentStatus, boolean isTransfer, boolean isTransferDepartment) {
        super(accountId, accountName, password, departmentId);
        this.multiMajorId = multiMajorId;
        this.subMajorId = subMajorId;
        this.beforeMajorId = beforeMajorId;
        this.studentStatus = studentStatus;
        this.isTransfer = isTransfer;
        this.isTransferDepartment = isTransferDepartment;
    }
}
