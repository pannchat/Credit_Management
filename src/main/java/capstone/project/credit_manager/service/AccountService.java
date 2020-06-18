package capstone.project.credit_manager.service;

import capstone.project.credit_manager.domain.Department;
import capstone.project.credit_manager.domain.accounts.Account;
import capstone.project.credit_manager.domain.accounts.LoggedInAccount;
import capstone.project.credit_manager.domain.accounts.Manager;
import capstone.project.credit_manager.domain.accounts.Student;
import capstone.project.credit_manager.repository.DepartmentRepository;
import capstone.project.credit_manager.repository.account.AccountRepository;
import capstone.project.credit_manager.service.exception.NotFoundDataException;
import capstone.project.credit_manager.web.exception.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class AccountService implements UserDetailsService {

    private final DepartmentRepository departmentRepository;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public Account authenticate(LoginInfoRequestDto loginInfoRequestDto) {
        Account account = accountRepository.findByAccountId(loginInfoRequestDto.getAccountId())
                .orElseThrow(() -> new NotFoundDataException(new ErrorResponse("FAILED_LOGIN", "로그인에 실패하였습니다.")));

        if (!account.isMatchPassword(passwordEncoder, loginInfoRequestDto.getPassword())) {
            throw new NotFoundDataException(new ErrorResponse("FAILED_LOGIN", "로그인에 실패하였습니다."));
        }

        return account;
    }

    @Override
    public UserDetails loadUserByUsername(String accountId) throws UsernameNotFoundException {
        Account account = accountRepository.findByAccountId(accountId)
                .orElseThrow(() -> new NotFoundDataException(new ErrorResponse("FAILED_LOGIN", "로그인에 실패하였습니다.")));

        return new LoggedInAccount(account);
    }

    public Account joinStudent(SignupStudentInfoDto signUpInfo) {
        Student newStudent = createStudent(signUpInfo);

        return accountRepository.save(newStudent);
    }

    private Student createStudent(SignupStudentInfoDto signUpInfo) {
        Department major = departmentRepository.findById(signUpInfo.getDepartmentId())
                .orElseThrow(() -> new NotFoundDataException(new ErrorResponse("NOT_FOUND_MAJOR", "없는 학과입니다.")));

        Department multiMajor = null, subMajor = null, beforeMajor = null;
        if (signUpInfo.getBeforeMajorId() != null) {
            beforeMajor = departmentRepository.findById(signUpInfo.getBeforeMajorId()).orElse(null);
        }
        if (signUpInfo.getMultiMajorId() != null) {
            multiMajor = departmentRepository.findById(signUpInfo.getMultiMajorId())
                    .orElseThrow(() -> new NotFoundDataException(new ErrorResponse("NOT_FOUND_MAJOR", "없는 학과입니다.")));
        }
        if (signUpInfo.getSubMajorId() != null) {
            subMajor = departmentRepository.findById(signUpInfo.getSubMajorId())
                    .orElseThrow(() -> new NotFoundDataException(new ErrorResponse("NOT_FOUND_MAJOR", "없는 학과입니다.")));
        }

        return Student.builder()
                .accountId(signUpInfo.getAccountId())
                .password(signUpInfo.getPassword())
                .name(signUpInfo.getUsername())
                .major(major)
                .beforeMajor(beforeMajor)
                .multiMajor(multiMajor)
                .subMajor(subMajor)
                .studentStatus(signUpInfo.getStudentStatus())
                .isTransfer(signUpInfo.isTransfer())
                .isTransferDepartment(signUpInfo.isTransferDepartment())
                .build();
    }

    public Account joinManager(SignupManagerInfoDto signupManagerInfoDto) {
        Manager newManager = createManager(signupManagerInfoDto);

        return accountRepository.save(newManager);
    }

    private Manager createManager(SignupManagerInfoDto signupManagerInfoDto) {
        Department major = departmentRepository.findById(signupManagerInfoDto.getDepartmentId())
                .orElseThrow(() -> new NotFoundDataException(new ErrorResponse("NOT_FOUND_MAJOR", "없는 학과입니다.")));

        return Manager.builder()
                .accountId(signupManagerInfoDto.getAccountId())
                .password(signupManagerInfoDto.getPassword())
                .name(signupManagerInfoDto.getUsername())
                .major(major)
                .build();
    }

    public Account updateAccount(LoggedInAccount loggedInAccount, SignupManagerInfoDto signupManagerInfoDto) {
        Account account = accountRepository.findById(loggedInAccount.getId())
                .orElseThrow(() -> new NotFoundDataException(new ErrorResponse("NOT_FOUND_ACCOUNT", "회원을 찾을 수 없습니다.")));
        Account updateAccount;
        if (loggedInAccount.isStudent()) {
            updateAccount = createStudent((SignupStudentInfoDto) signupManagerInfoDto);
        } else {
            updateAccount = createManager(signupManagerInfoDto);
        }

        account.update(updateAccount);
        return account;
    }

    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundDataException(new ErrorResponse("NOT_FOUND_ACCOUNT", "회원을 찾을 수 없습니다.")));

        accountRepository.delete(account);
    }
}
