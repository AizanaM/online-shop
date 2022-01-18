package kg.easyit.onlineshop.model.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;
import lombok.experimental.FieldDefaults;

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
    BasketDto basketDto;
    String password;
}
