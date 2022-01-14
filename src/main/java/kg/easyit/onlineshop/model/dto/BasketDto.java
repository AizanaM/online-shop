package kg.easyit.onlineshop.model.dto;

import kg.easyit.onlineshop.model.entity.Order;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BasketDto {

    Long id;
    UserDto user;
    List<Order> orders;
    BigDecimal totalSum;

}
