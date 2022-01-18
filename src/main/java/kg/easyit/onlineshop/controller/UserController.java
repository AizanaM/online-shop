package kg.easyit.onlineshop.controller;

import kg.easyit.onlineshop.model.request.CreateUserRequest;
import kg.easyit.onlineshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateUserRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(request));
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }
}
