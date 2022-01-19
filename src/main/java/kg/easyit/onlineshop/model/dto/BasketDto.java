package kg.easyit.onlineshop.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BasketDto {

    Long id;
    Date dateCreated;
    Date dateUpdated;
    UserDto user;
    List<OrderDto> orders;
    BigDecimal totalSum;

}
