package kg.easyit.onlineshop.service.impl;

import kg.easyit.onlineshop.mapper.TransactionMapper;
import kg.easyit.onlineshop.model.dto.TransactionDto;
import kg.easyit.onlineshop.model.request.CreateTransactionRequest;
import kg.easyit.onlineshop.repository.TransactionRepository;
import kg.easyit.onlineshop.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public TransactionDto findById(Long id) {
        return TransactionMapper.INSTANSE.toDto(transactionRepository.findById(id).orElseThrow(()-> new RuntimeException("not found")));
    }

    @Override
    public TransactionDto create(CreateTransactionRequest createTransactionRequest) {
        return null;
    }
}
