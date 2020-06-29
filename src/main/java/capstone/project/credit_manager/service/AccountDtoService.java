package capstone.project.credit_manager.service;

import capstone.project.credit_manager.domain.accounts.LoggedInAccount;
import capstone.project.credit_manager.domain.accounts.Manager;
import capstone.project.credit_manager.domain.accounts.Student;
import capstone.project.credit_manager.repository.account.AccountRepository;
import capstone.project.credit_manager.repository.account.dto.CommonAccountInfoDto;
import capstone.project.credit_manager.repository.account.dto.StudentInfoDto;
import capstone.project.credit_manager.service.exception.NotFoundDataException;
import capstone.project.credit_manager.web.exception.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class AccountDtoService {
    private final AccountRepository accountRepository;

    public Object getAccountInfo(LoggedInAccount loggedInAccount) {
        if (loggedInAccount.isStudent()) {
            Student student = accountRepository.findStudentById(loggedInAccount.getId())
                    .orElseThrow(() -> new NotFoundDataException(new ErrorResponse("NOT_FOUND_ACCOUNT", "회원을 찾을 수 없습니다.")));

            CommonAccountInfoDto commonAccountInfoDto = CommonAccountInfoDto.builder()
                    .id(student.getId())
                    .accountId(student.getAccountId())
                    .username(student.getName())
                    .departmentName(student.getMajor().getName())
                    .build();

            return StudentInfoDto.builder()
                    .commonAccountInfoDto(commonAccountInfoDto)
                    .multiMajorName(student.getMultiMajorName())
                    .subMajorName(student.getSubMajorName())
                    .beforeMajorName(student.getBeforeMajorName())
                    .isTransfer(student.isTransfer())
                    .isTransferDepartment(student.isTransferDepartment())
                    .studentStatus(student.getStudentStatus())
                    .build();
        } else {
            Manager manager = accountRepository.findManagerById(loggedInAccount.getId())
                    .orElseThrow(() -> new NotFoundDataException(new ErrorResponse("NOT_FOUND_ACCOUNT", "회원을 찾을 수 없습니다.")));

            return CommonAccountInfoDto.builder()
                    .id(manager.getId())
                    .accountId(manager.getAccountId())
                    .username(manager.getName())
                    .departmentName(manager.getMajor().getName())
                    .build();
        }
    }
}
