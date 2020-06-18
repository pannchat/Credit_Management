package capstone.project.credit_manager.repository.account.dto;

import capstone.project.credit_manager.domain.StudentStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StudentInfoDto {
    private CommonAccountInfoDto commonAccountInfoDto;
    private String multiMajorName;
    private String subMajorName;
    private String beforeMajorName;
    private StudentStatus studentStatus;
    private boolean isTransfer;
    private boolean isTransferDepartment;

    @Builder
    public StudentInfoDto(CommonAccountInfoDto commonAccountInfoDto, String multiMajorName, String subMajorName, String beforeMajorName, StudentStatus studentStatus, boolean isTransfer, boolean isTransferDepartment) {
        this.commonAccountInfoDto = commonAccountInfoDto;
        this.multiMajorName = multiMajorName;
        this.subMajorName = subMajorName;
        this.beforeMajorName = beforeMajorName;
        this.studentStatus = studentStatus;
        this.isTransfer = isTransfer;
        this.isTransferDepartment = isTransferDepartment;
    }
}
