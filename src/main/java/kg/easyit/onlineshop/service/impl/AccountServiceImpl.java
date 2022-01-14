package kg.easyit.onlineshop.service.impl;

import kg.easyit.onlineshop.mapper.AccountMapper;
import kg.easyit.onlineshop.mapper.UserMapper;
import kg.easyit.onlineshop.model.dto.AccountDto;
import kg.easyit.onlineshop.model.dto.UserDto;
import kg.easyit.onlineshop.model.entity.Account;
import kg.easyit.onlineshop.model.request.CreateAccountRequest;
import kg.easyit.onlineshop.model.request.UpdateAccountRequest;
import kg.easyit.onlineshop.repository.AccountRepository;
import kg.easyit.onlineshop.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public AccountDto create(CreateAccountRequest createAccountRequest) {
        UserDto userDto = accountRepository
                .findByIdAndIsActiveTrue(createAccountRequest.getUserId()).orElseThrow(() -> new RuntimeException("not found")));
        Account account = Account.builder()
                .accountName(createAccountRequest.getAccountName())
                .availableMoney(createAccountRequest.getAvailableMoney())
                .user(UserMapper.INSTANSE.toEntity(userDto))
                .build();

        accountRepository.save(account);

        return AccountMapper.INSTANSE.toDto(account);
    }

    @Override
    public AccountDto update(UpdateAccountRequest updateAccountRequest) {
        return null;
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
}
