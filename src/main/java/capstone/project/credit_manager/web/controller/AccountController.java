package capstone.project.credit_manager.web.controller;

import capstone.project.credit_manager.domain.accounts.LoggedInAccount;
import capstone.project.credit_manager.service.AccountDtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AccountController {
    private final AccountDtoService accountDtoService;

    @GetMapping("/accounts")
    public ResponseEntity<Object> getAccountInfo(@AuthenticationPrincipal LoggedInAccount loggedInAccount) {
        return new ResponseEntity<>(accountDtoService.getAccountInfo(loggedInAccount), HttpStatus.OK);
    }
}
