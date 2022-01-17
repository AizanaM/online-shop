package kg.easyit.onlineshop.controller;

import kg.easyit.onlineshop.model.request.CreateAccountRequest;
import kg.easyit.onlineshop.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateAccountRequest createAccountRequest){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(accountService.create(createAccountRequest));
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }


}
