package kg.easyit.onlineshop.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDto {

    Long id;
    BasketDto basket;
    ProductDto product;
    Integer quantityOfProducts;
    Double total;

}
