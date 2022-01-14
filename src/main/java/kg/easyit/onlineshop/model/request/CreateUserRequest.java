package kg.easyit.onlineshop.model.request;


import kg.easyit.onlineshop.model.entity.Basket;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateUserRequest {
    String firstName;
    String lastName;
    String username;
    String email;
    String phoneNumber;
    Basket basket;
    String password;
}
