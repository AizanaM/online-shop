package kg.easyit.onlineshop.controller;

import kg.easyit.onlineshop.model.request.TransactionDetails;
import kg.easyit.onlineshop.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    @PreAuthorize("hasAuthority('TRANSACTION_CREATE')")
    @GetMapping("/create")
    public ResponseEntity<?> findByAccountToId(@RequestBody TransactionDetails details) {
        try {
            return ResponseEntity.ok(transactionService.create(details));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('TRANSACTION_READ')")
    @GetMapping("/find-transactions-to")
    public ResponseEntity<?> findByAccountToId(Long id) {
        try {
            return ResponseEntity.ok(transactionService.findByAccountToId(id));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('TRANSACTION_READ')")
    @GetMapping("/find-transactions-from")
    public ResponseEntity<?> findByAccountFrom(Long id) {
        try {
            return ResponseEntity.ok(transactionService.findByAccountFromId(id));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('TRANSACTION_READ')")
    @GetMapping("/find-all")
    public ResponseEntity<?> findAllTransactions(@RequestBody Long accountId) {
        try {
            return ResponseEntity.ok().body(transactionService.findAllTransaction(accountId));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

}
