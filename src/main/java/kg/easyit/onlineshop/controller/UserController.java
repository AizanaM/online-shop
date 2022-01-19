package kg.easyit.onlineshop.controller;
import kg.easyit.onlineshop.model.dto.UserDto;
import kg.easyit.onlineshop.model.request.CreateUserRequest;
import kg.easyit.onlineshop.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasAuthority('USER_CREATE')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateUserRequest createUserRequest){
        try{
            log.info("Creating user");
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(createUserRequest));
        }catch (RuntimeException ex){
            log.error("User creation is failed");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }


    @PreAuthorize("hasAuthority('USER_READ')")
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id){
        try{
            log.info("Reading user by id");
            return ResponseEntity.ok(userService.findById(id));
        }catch (RuntimeException ex){
            log.error("Reading is failed");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }


    @PreAuthorize("hasAuthority('USER_UPDATE')")
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody UserDto userDto){
        try{
            log.info("Updating user");
            return ResponseEntity.ok(userService.update(userDto));
        }catch (RuntimeException ex){
            log.error("User updating is failed");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }


    @PreAuthorize("hasAuthority('USER_DELETE')")
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@PathVariable Long id ){
        try{
            log.info("Delete user");
            return ResponseEntity.ok(userService.delete(id));
        }catch (RuntimeException ex){
            log.error("User deleting is failed");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }
}
