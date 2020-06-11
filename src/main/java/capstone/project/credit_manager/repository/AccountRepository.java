package capstone.project.credit_manager.repository;

import capstone.project.credit_manager.domain.accounts.Account;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @EntityGraph(attributePaths = {"major"})
    Optional<Account> findByAccountId(String accountId);
}
