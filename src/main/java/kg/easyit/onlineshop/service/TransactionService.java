package kg.easyit.onlineshop.service;

import kg.easyit.onlineshop.model.dto.TransactionDto;
import kg.easyit.onlineshop.model.request.CreateTransactionRequest;
import org.springframework.stereotype.Service;


@Service
public interface TransactionService {
    TransactionDto findById(Long id);
    TransactionDto create(CreateTransactionRequest createTransactionRequest);
}
