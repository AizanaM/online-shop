package kg.easyit.onlineshop.controller;

import kg.easyit.onlineshop.model.request.CreateAccountRequest;
import kg.easyit.onlineshop.model.request.UpdateAccountRequest;
import kg.easyit.onlineshop.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account")
public class AccountController {
    private final AccountService accountService;

    @PreAuthorize("hasAuthority('ACCOUNT_CREATE')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateAccountRequest createAccountRequest) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(accountService.create(createAccountRequest));
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ACCOUNT_UPDATE')")
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody UpdateAccountRequest updateAccountRequest) {
        try {
            return ResponseEntity.ok().body(accountService.update(updateAccountRequest));
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ACCOUNT_DELETE')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(accountService.delete(id));
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ACCOUNT_READ')")
    @GetMapping("/find/{id}")
    public ResponseEntity<?> find(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(accountService.findById(id));
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }
}
