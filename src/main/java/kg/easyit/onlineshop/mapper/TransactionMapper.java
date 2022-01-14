package kg.easyit.onlineshop.mapper;

import kg.easyit.onlineshop.model.dto.TransactionDto;
import kg.easyit.onlineshop.model.entity.Transaction;
import org.mapstruct.factory.Mappers;

public interface TransactionMapper extends BaseMapper<Transaction, TransactionDto> {
    TransactionMapper INSTANSE = Mappers.getMapper(TransactionMapper.class);
}
