package capstone.project.credit_manager.web.controller;

import capstone.project.credit_manager.config.auth.JwtTokenProvider;
import capstone.project.credit_manager.domain.accounts.Account;
import capstone.project.credit_manager.domain.accounts.LoggedInAccount;
import capstone.project.credit_manager.service.*;
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
    public ResponseEntity signUpStudent(@RequestBody SignUpStudentInfoDto signupStudentInfoDto) {
        accountService.joinStudent(signupStudentInfoDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/manager")
    public ResponseEntity signUpStudent(@RequestBody SignUpManagerInfoDto signupManagerInfoDto) {
        accountService.joinManager(signupManagerInfoDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/student")
    public ResponseEntity updateStudent(@RequestBody SignUpStudentInfoDto updatedAccountInfoDto, @AuthenticationPrincipal LoggedInAccount loggedInAccount) {
        Account account = accountService.updateAccount(loggedInAccount, updatedAccountInfoDto);
        String accessToken = jwtTokenProvider.createToken(account.getAccountId());
        return new ResponseEntity(JwtAccessToken.builder().accessToken(accessToken).build(), HttpStatus.OK);
    }

    @PutMapping("/manager")
    public ResponseEntity updateManager(@RequestBody SignUpManagerInfoDto updatedManagerInfoDto, @AuthenticationPrincipal LoggedInAccount loggedInAccount) {
        Account account = accountService.updateAccount(loggedInAccount, updatedManagerInfoDto);
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


