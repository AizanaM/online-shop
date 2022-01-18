package kg.easyit.onlineshop.controller;

import kg.easyit.onlineshop.exceptions.ProductNotFoundException;
import kg.easyit.onlineshop.model.dto.ProductDto;
import kg.easyit.onlineshop.model.request.CreateProductRequest;
import kg.easyit.onlineshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateProductRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(productService.create(request));
        } catch (ProductNotFoundException ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(ex.getMessage());
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(productService.findById(id));
        } catch (ProductNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(ex.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody ProductDto productDto) {
        try {
            return ResponseEntity.ok(productService.update(productDto));
        } catch (ProductNotFoundException ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(ex.getMessage());
        }
    }
}
