package kg.easyit.onlineshop.model.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateOrderRequest {

    @NotNull
    @Positive
    Long basketId; // ?

    @NotNull
    @Positive
    Long productId;

    @NotNull
    @Positive
    Integer quantityOfProducts;

    @NotNull
    @Positive
    BigDecimal total;

}
