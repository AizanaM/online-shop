package kg.easyit.onlineshop.model.dto;

import kg.easyit.onlineshop.model.entity.Order;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BasketDto {

    Long id;
    UserDto user;
    Order[] orders;
    Double totalSum;

}
