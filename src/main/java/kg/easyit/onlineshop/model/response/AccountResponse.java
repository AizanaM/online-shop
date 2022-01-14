package kg.easyit.onlineshop.model.response;

import kg.easyit.onlineshop.model.dto.TransactionDto;
import kg.easyit.onlineshop.model.dto.userDto;

import java.util.List;

public class AccountResponse {
    Long id;
    String accountName;
    Long availableMoney;
    userDto userDto;
    List<TransactionDto> transactions;
}
