package capstone.project.credit_manager;

import capstone.project.credit_manager.domain.Department;
import capstone.project.credit_manager.domain.accounts.Account;
import capstone.project.credit_manager.domain.accounts.Role;
import capstone.project.credit_manager.domain.accounts.Student;
import capstone.project.credit_manager.repository.AccountRepository;
import capstone.project.credit_manager.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
@Transactional
public class ApplicationRunner implements CommandLineRunner {
    private final AccountRepository accountRepository;
    private final DepartmentRepository departmentRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        Department department = Department.builder()
                .name("컴퓨터공학과")
                .build();

        Department savedDepartment = departmentRepository.save(department);

        Student student = Student.builder()
                .accountId("201513379")
                .password(passwordEncoder.encode("password"))
                .major(savedDepartment)
                .role(Role.STUDENT)
                .build();

        accountRepository.save(student);
    }
}