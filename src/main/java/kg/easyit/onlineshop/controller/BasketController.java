package kg.easyit.onlineshop.controller;

import kg.easyit.onlineshop.model.request.CreateBasketRequest;
import kg.easyit.onlineshop.service.BasketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/basket")
public class BasketController {

    private final BasketService basketService;

    @PreAuthorize("hasAuthority('BASKET_CREATE')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateBasketRequest request) {
        try {
            log.info("Creating basket.");
            return ResponseEntity.status(HttpStatus.CREATED).body(basketService.create(request));
        } catch (RuntimeException ex) {
            log.error("Basket creation failed");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('BASKET_READ')")
    @GetMapping("/read/{id}")
    public ResponseEntity<?> read(@PathVariable Long id) {
        try {
            log.info("Reading basket with id=" + id);
            return ResponseEntity.ok(basketService.find(id));
        } catch (RuntimeException ex) {
            log.error("Basket reading failed. Basket with id=" + id + " is not found.");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('BASKET_READ')")
    @GetMapping("/read-all/{userId}")
    public ResponseEntity<?> readAll(@PathVariable Long userId) {
        try {
            log.info("Reading baskets with user id=" + userId);
            return ResponseEntity.ok(basketService.find(userId));
        } catch (RuntimeException ex) {
            log.error("Reading baskets failed. Baskets with user id" + userId + " are not found.");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

//    @PreAuthorize("hasAuthority('BASKET_READ')")
//    @PutMapping("/clear")
//    public ResponseEntity<?> clear(@RequestBody BasketDto basketDto) { // ?
//        try {
//            log.info("Clearing basket with id=" + basketDto.getId());
//            return ResponseEntity.ok(basketService.clearBasket(basketDto));
//        } catch (RuntimeException ex) {
//            log.error("Basket clearing failed. Basket with id=" + basketDto.getId() + " is not found.");
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
//        }
//    }
}
