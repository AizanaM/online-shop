package kg.easyit.onlineshop.model.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateBasketRequest {

    @NotNull
    @Positive
    Long userId;

    @NotNull
    @Positive
    BigDecimal totalSum;

}
