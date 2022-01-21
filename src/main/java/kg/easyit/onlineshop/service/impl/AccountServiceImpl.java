package kg.easyit.onlineshop.service.impl;

import kg.easyit.onlineshop.exceptions.AccountNotFoundException;
import kg.easyit.onlineshop.mapper.AccountMapper;
import kg.easyit.onlineshop.model.dto.AccountDto;
import kg.easyit.onlineshop.model.entity.Account;
import kg.easyit.onlineshop.model.request.CreateAccountRequest;
import kg.easyit.onlineshop.model.request.UpdateAccountRequest;
import kg.easyit.onlineshop.model.response.MessageResponse;
import kg.easyit.onlineshop.repository.AccountRepository;
import kg.easyit.onlineshop.service.AccountService;
import kg.easyit.onlineshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserService userService;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository,@Lazy UserService userService) {
        this.accountRepository = accountRepository;
        this.userService = userService;
    }

    @Value("${account.details}")
    public static Long shopAccountId;

    @Override
    public MessageResponse create(CreateAccountRequest createAccountRequest) {
        userService.setAccount(createAccountRequest);
        return new MessageResponse("account created");
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
    public void save(Account account) {
        accountRepository.save(account);
    }

    @Override
    public AccountDto addMoney(BigDecimal money) {
        if (money.doubleValue() <= 0){
            throw new TaskRejectedException("money < 0");
        }

        return accountRepository.findById(1L).map(account -> {
            account.setAvailableMoney(account.getAvailableMoney().add(money));
            return AccountMapper.INSTANSE.toDto(accountRepository.save(account));
        }).orElseThrow(()-> new AccountNotFoundException(""));
    }

    @Override
    public AccountDto subtractMoney(Long id, BigDecimal money) {
        Account account = accountRepository.getById(id);

        if (account.getAvailableMoney().doubleValue() <= money.doubleValue()){
            throw new TaskRejectedException("you sending more than you have");
        }
        if (money.compareTo(BigDecimal.ZERO) < 0) {
            throw new TaskRejectedException("money is less than 0");
        }

        account.setAvailableMoney(account.getAvailableMoney().subtract(money));
        return AccountMapper.INSTANSE.toDto(account);
    }
}
