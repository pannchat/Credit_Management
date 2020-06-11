package capstone.project.credit_manager.web.controller;

import capstone.project.credit_manager.config.auth.JwtTokenProvider;
import capstone.project.credit_manager.domain.accounts.Account;
import capstone.project.credit_manager.service.AccountService;
import capstone.project.credit_manager.service.LoginInfoRequestDto;
import capstone.project.credit_manager.web.controller.vo.JwtAccessToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/accounts")
@RestController
public class AccountRestApiController {
    private final AccountService accountService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginInfoRequestDto loginInfoRequestDto) {
        Account account = accountService.authenticate(loginInfoRequestDto);
        String accessToken = jwtTokenProvider.createToken(account.getAccountId());

        return new ResponseEntity(JwtAccessToken.builder().accessToken(accessToken).build(), HttpStatus.OK);
    }
}


