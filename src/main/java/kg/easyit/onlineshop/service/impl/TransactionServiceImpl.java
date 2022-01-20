package kg.easyit.onlineshop.service.impl;

import kg.easyit.onlineshop.mapper.AccountMapper;
import kg.easyit.onlineshop.mapper.TransactionMapper;
import kg.easyit.onlineshop.model.dto.AccountDto;
import kg.easyit.onlineshop.model.dto.TransactionDto;
import kg.easyit.onlineshop.model.entity.Transaction;
import kg.easyit.onlineshop.model.request.TransactionDetails;
import kg.easyit.onlineshop.repository.TransactionRepository;
import kg.easyit.onlineshop.service.AccountService;
import kg.easyit.onlineshop.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountService accountService;

    @Override
    public TransactionDto findById(Long id) {
        return TransactionMapper.INSTANSE.toDto(transactionRepository.findById(id).orElseThrow(() -> new RuntimeException("not found")));
    }

    @Override
    public TransactionDto create(TransactionDetails transactionDetails) {

        AccountDto accountTo = accountService.addMoney(transactionDetails.getAmount());
        log.info(String.valueOf(accountTo.getId()));
        AccountDto accountFrom = accountService.subtractMoney(transactionDetails.getAccountFromId(), transactionDetails.getAmount());
        log.info(String.valueOf(accountFrom.getId()));
        if (accountFrom.getAvailableMoney().doubleValue() < transactionDetails.getAmount().doubleValue()) {
            throw new RuntimeException("Transaction not acceptable");
        }

        Transaction transaction = Transaction.builder()
                .amount(transactionDetails.getAmount())
                .purpose(transactionDetails.getPurpose())
                .accountFromId(AccountMapper.INSTANSE.toEntity(accountFrom))
                .accountToId(AccountMapper.INSTANSE.toEntity(accountTo))
                .build();

        transactionRepository.save(transaction);

        return TransactionMapper.INSTANSE.toDto(transaction);
    }

    @Override
    public List<TransactionDto> findByAccountFromId(Long accountId) {

        return TransactionMapper.INSTANSE.toDtoList(transactionRepository.findByAccountFromId(accountId)
                .orElseThrow(() -> new RuntimeException("not found")));
    }

    @Override
    public List<TransactionDto> findByAccountToId(Long accountId) {

        return TransactionMapper.INSTANSE.toDtoList(transactionRepository.findByAccountToId(accountId)
                .orElseThrow(() -> new RuntimeException("not found")));
    }

    @Override
    public List<TransactionDto> findAllTransaction(Long accountId) {
        List<TransactionDto> transactions = findByAccountFromId(accountId);
        transactions.addAll(findByAccountToId(accountId));
        return transactions.stream().sorted().collect(Collectors.toList());
    }
}
