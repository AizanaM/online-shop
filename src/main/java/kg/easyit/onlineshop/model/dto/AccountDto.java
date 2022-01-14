package kg.easyit.onlineshop.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountDto {
    Long id;
    String accountName;
    Long availableMoney;
    userDto userDto;
    List<TransactionDto> transactions;
}
