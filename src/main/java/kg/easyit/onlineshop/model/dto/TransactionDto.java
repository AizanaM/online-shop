package kg.easyit.onlineshop.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionDto {
    Long id;
    String purpose;
    BigDecimal amount;
    AccountDto accountToID;
    AccountDto accountFromId;
}
