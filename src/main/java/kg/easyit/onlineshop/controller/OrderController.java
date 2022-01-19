package kg.easyit.onlineshop.controller;

import kg.easyit.onlineshop.model.dto.OrderDto;
import kg.easyit.onlineshop.model.request.CreateOrderRequest;
import kg.easyit.onlineshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    @PreAuthorize("hasAuthority('ORDER_CREATE')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateOrderRequest request) {
        try {
            log.info("Creating order.");
            return ResponseEntity.status(HttpStatus.CREATED).body(orderService.create(request));
        } catch (RuntimeException ex) {
            log.error("Order creation failed. Quantity of product units=" + request.getQuantityOfProducts() +
                    " for this order is unavailable."); // ???
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ORDER_CREATE')")
    @PostMapping("/create-all")
    public ResponseEntity<?> createAll(@RequestBody List<CreateOrderRequest> requests) {
        try {
            log.info("Creating all orders.");
            return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createAll(requests));
        } catch (RuntimeException ex) {
            log.error("Failed creating all orders. ?");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ORDER_READ')")
    @GetMapping("/read/{id}")
    public ResponseEntity<?> read(@PathVariable Long id) {
        try {
            log.info("Reading order with id=" + id);
            return ResponseEntity.ok(orderService.find(id));
        } catch (RuntimeException ex) {
            log.error("Order reading failed. Order with id=" + id + " is not found.");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ORDER_READ')")
    @GetMapping("/read-all")
    public ResponseEntity<?> readAll(Long basketId) {
        try {
            log.info("Reading all orders with basket id=" + basketId);
            return ResponseEntity.ok(orderService.findByBasket(basketId));
        } catch (RuntimeException ex) {
            log.error("Failed reading all orders for this basket. Orders with basket id=" + basketId
                    + " are not found.");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ORDER_UPDATE')")
    @PutMapping("/update-quantity")
    public ResponseEntity<?> updateProductQuantity(@RequestBody OrderDto orderDto) {
        try {
            log.info("Updating order.");
            return ResponseEntity.ok(orderService.updateProductQuantity(orderDto));
        } catch (RuntimeException ex) {
            log.error("Order updating failed. Order with id=" + orderDto.getId() + " is not found.");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ORDER_UPDATE')")
    @PutMapping("/cancel/{id}")
    public ResponseEntity<?> cancel(@PathVariable Long id) {
        try {
            log.info("Cancelling order.");
            return ResponseEntity.ok(orderService.cancel(id));
        } catch (RuntimeException ex) {
            log.error("Order cancelling failed. Order with id=" + id + " is not found.");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ORDER_UPDATE')")
    @PutMapping("/cancel-all") //???
    public ResponseEntity<?> cancelAll(@RequestBody List<Long> ids) {
        try {
            log.info("Cancelling orders.");
            return ResponseEntity.ok(orderService.cancelAll(ids));
        } catch (RuntimeException ex) {
            log.error("Order cancelling failed. Orders with ids=" + ids + " are not found.");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

}
