package kg.easyit.onlineshop.model.dto;

import kg.easyit.onlineshop.model.entity.Account;
import kg.easyit.onlineshop.model.entity.Basket;
import kg.easyit.onlineshop.model.entity.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    Long id;
    String firstName;
    String lastName;
    String username;
    String email;
    String phoneNumber;
    List<AccountDto> account;
    List<BasketDto> baskets;
    String password;
    Boolean isActive;
    RoleDto roleDto;

}
