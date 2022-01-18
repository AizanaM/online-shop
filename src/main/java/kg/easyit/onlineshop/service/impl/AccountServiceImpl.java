package kg.easyit.onlineshop.service.impl;

import kg.easyit.onlineshop.exceptions.AccountNotFoundException;
import kg.easyit.onlineshop.mapper.AccountMapper;
import kg.easyit.onlineshop.mapper.UserMapper;
import kg.easyit.onlineshop.model.dto.AccountDto;
import kg.easyit.onlineshop.model.dto.UserDto;
import kg.easyit.onlineshop.model.entity.Account;
import kg.easyit.onlineshop.model.request.CreateAccountRequest;
import kg.easyit.onlineshop.model.request.UpdateAccountRequest;
import kg.easyit.onlineshop.repository.AccountRepository;
import kg.easyit.onlineshop.service.AccountService;
import kg.easyit.onlineshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserService userService;

    @Value("${account.details}")
    private Long shopAccountId;


    @Override
    public AccountDto create(CreateAccountRequest createAccountRequest) {
        UserDto userDto = (userService
                .findById(createAccountRequest.getUserId()));

        Account account = Account.builder()
                .accountName(createAccountRequest.getAccountName())
                .availableMoney(createAccountRequest.getAvailableMoney())
                .user(UserMapper.INSTANCE.toEntity(userDto))
                .build();

        accountRepository.save(account);

        return AccountMapper.INSTANSE.toDto(account);
    }

    @Override
    public AccountDto update(UpdateAccountRequest updateAccountRequest) {

        Account account = accountRepository.findByIdAndIsActiveTrue(updateAccountRequest.getId()).map(account1 -> {
            account1.setAccountName(updateAccountRequest.getAccountName());
            account1.setAvailableMoney(updateAccountRequest.getAvailableMoney());
            return accountRepository.save(account1);
        }).orElseThrow(() -> new AccountNotFoundException("not found"));
        return AccountMapper.INSTANSE.toDto(account);
    }

    @Override
    public AccountDto findById(Long id) {
        return AccountMapper.INSTANSE
                .toDto(accountRepository.findByIdAndIsActiveTrue(id).orElseThrow(() -> new RuntimeException("not found")));
    }

    @Override
    public AccountDto delete(Long id) {
        Account account = accountRepository.findByIdAndIsActiveTrue(id).map(account1 -> {
            account1.setIsActive(false);
            return accountRepository.save(account1);
        }).orElseThrow(() -> new RuntimeException("not found"));
        return AccountMapper.INSTANSE.toDto(account);
    }

    @Override
    public void save(AccountDto accountDto) {
        accountRepository.save(AccountMapper.INSTANSE.toEntity(accountDto));
    }

    @Override
    public AccountDto addMoney(BigDecimal money) {
        if (money.compareTo(BigDecimal.ZERO)<0){
            throw new TaskRejectedException("money < 0");
        }

        return accountRepository.findById(shopAccountId).map(account -> {
            account.setAvailableMoney(account.getAvailableMoney().add(money));
            return AccountMapper.INSTANSE.toDto(accountRepository.save(account));
        }).orElseThrow(()-> new AccountNotFoundException("not found"));
    }

    @Override
    public AccountDto subtractMoney(Long id, BigDecimal money) {

        if (money.compareTo(BigDecimal.ZERO)<0){
            throw new TaskRejectedException("money < 0");
        }

        return accountRepository.findById(id).map(account -> {
            account.setAvailableMoney(account.getAvailableMoney().subtract(money));
            return AccountMapper.INSTANSE.toDto(accountRepository.save(account));
        }).orElseThrow(()-> new AccountNotFoundException("not found"));
    }
}
