package kg.easyit.onlineshop.mapper;

import kg.easyit.onlineshop.model.dto.AccountDto;
import kg.easyit.onlineshop.model.entity.Account;
import org.mapstruct.factory.Mappers;

public interface AccountMapper extends BaseMapper<Account, AccountDto> {
    AccountMapper INSTANSE = Mappers.getMapper(AccountMapper.class);
}
