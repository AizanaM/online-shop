package kg.easyit.onlineshop.service;

import kg.easyit.onlineshop.model.dto.TransactionDto;
import kg.easyit.onlineshop.model.request.TransactionDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {
    TransactionDto findById(Long id);
    TransactionDto create(TransactionDetails createTransactionRequest);
    List<TransactionDto> findByAccountFromId(Long accountId);
    List<TransactionDto> findByAccountToId(Long accountId);
    List<TransactionDto> findAllTransaction(Long accountId);
}
