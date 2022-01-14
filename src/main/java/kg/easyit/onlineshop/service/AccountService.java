package kg.easyit.onlineshop.service;

import kg.easyit.onlineshop.model.dto.AccountDto;
import kg.easyit.onlineshop.model.request.CreateAccountRequest;
import kg.easyit.onlineshop.model.request.UpdateAccountRequest;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    AccountDto create(CreateAccountRequest createAccountRequest);
    AccountDto update(UpdateAccountRequest updateAccountRequest);
    AccountDto findById(Long id);
    AccountDto delete(Long id);
}
