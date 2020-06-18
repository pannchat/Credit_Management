package capstone.project.credit_manager.repository.account;

import capstone.project.credit_manager.domain.accounts.Account;
import capstone.project.credit_manager.domain.accounts.Manager;
import capstone.project.credit_manager.domain.accounts.Student;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @EntityGraph(attributePaths = {"major"})
    Optional<Account> findByAccountId(String accountId);

    @EntityGraph(attributePaths = {"major", "multiMajor", "subMajor", "beforeMajor"})
    Optional<Student> findStudentById(Long id);

    @EntityGraph(attributePaths = {"major"})
    Optional<Manager> findManagerById(Long id);
}
