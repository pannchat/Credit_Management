package capstone.project.credit_manager.service;

import capstone.project.credit_manager.common.ErrorResponse;
import capstone.project.credit_manager.domain.accounts.Account;
import capstone.project.credit_manager.domain.accounts.LoggedInAccount;
import capstone.project.credit_manager.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountService implements UserDetailsService {

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
}
