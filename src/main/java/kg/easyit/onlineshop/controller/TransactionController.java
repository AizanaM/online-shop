package kg.easyit.onlineshop.controller;

import kg.easyit.onlineshop.model.request.TransactionDetails;
import kg.easyit.onlineshop.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    @PreAuthorize("hasAuthority('TRANSACTION_CREATE')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody TransactionDetails details) {
        try {
            return ResponseEntity.ok(transactionService.create(details));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('TRANSACTION_READ')")
    @GetMapping("/find-transactions-to/{id}")
    public ResponseEntity<?> findByAccountToId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(transactionService.findByAccountToId(id));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('TRANSACTION_READ')")
    @GetMapping("/find-transactions-from/{id}")
    public ResponseEntity<?> findByAccountFrom(Long id) {
        try {
            return ResponseEntity.ok(transactionService.findByAccountFromId(id));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('TRANSACTION_READ')")
    @GetMapping("/find-all/{id}")
    public ResponseEntity<?> findAllTransactions(@PathVariable Long accountId) {
        try {
            return ResponseEntity.ok().body(transactionService.findAllTransaction(accountId));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

}
