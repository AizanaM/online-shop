package kg.easyit.onlineshop.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateAccountRequest {
    String accountName;
    BigDecimal availableMoney;
    Long userId;
}
