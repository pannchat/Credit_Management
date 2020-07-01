package capstone.project.credit_manager.web.controller;

import capstone.project.credit_manager.config.auth.JwtTokenProvider;
import capstone.project.credit_manager.domain.accounts.Account;
import capstone.project.credit_manager.domain.accounts.LoggedInAccount;
import capstone.project.credit_manager.service.AccountService;
import capstone.project.credit_manager.service.dto.AccountManagerInfoDto;
import capstone.project.credit_manager.service.dto.AccountStudentInfoDto;
import capstone.project.credit_manager.service.dto.LoginInfoRequestDto;
import capstone.project.credit_manager.web.controller.vo.JwtAccessToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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

    @PostMapping("/student")
    public ResponseEntity signUpStudent(@RequestBody AccountStudentInfoDto signupStudentInfoDto) {
        accountService.joinStudent(signupStudentInfoDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/manager")
    public ResponseEntity signUpStudent(@RequestBody AccountManagerInfoDto signupManagerInfoDto) {
        accountService.joinManager(signupManagerInfoDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/student")
    public ResponseEntity updateStudent(@RequestBody AccountStudentInfoDto updatedAccountInfoDto, @AuthenticationPrincipal LoggedInAccount loggedInAccount) {
        Account account = accountService.updateStudent(loggedInAccount, updatedAccountInfoDto);
        String accessToken = jwtTokenProvider.createToken(account.getAccountId());
        return new ResponseEntity(JwtAccessToken.builder().accessToken(accessToken).build(), HttpStatus.OK);
    }

    @PutMapping("/manager")
    public ResponseEntity updateManager(@RequestBody AccountManagerInfoDto updatedAccountInfoDto, @AuthenticationPrincipal LoggedInAccount loggedInAccount) {
        Account account = accountService.updateManager(loggedInAccount, updatedAccountInfoDto);
        String accessToken = jwtTokenProvider.createToken(account.getAccountId());
        return new ResponseEntity(JwtAccessToken.builder().accessToken(accessToken).build(), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity deleteAccount(@AuthenticationPrincipal LoggedInAccount loggedInAccount, HttpSession httpSession) {
        accountService.deleteAccount(loggedInAccount.getId());
        httpSession.invalidate();
        return new ResponseEntity(HttpStatus.OK);
    }
}


