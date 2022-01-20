package kg.easyit.onlineshop.model.dto;

import kg.easyit.onlineshop.model.enums.OrderStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDto {
    Long id;
    Date dateCreated;
    Date dateUpdated;
    BasketDto basket;
    ProductDto product;
    Integer quantityOfProducts;
    BigDecimal total;
    OrderStatus orderStatus;
}
