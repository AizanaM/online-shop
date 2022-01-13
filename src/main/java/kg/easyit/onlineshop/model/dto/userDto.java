package kg.easyit.onlineshop.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class userDto {
    String firstName;
    String lastName;
    String username;
    String password;
    String email;
    String phoneNumber;

}
