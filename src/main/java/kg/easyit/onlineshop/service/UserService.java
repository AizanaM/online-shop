package kg.easyit.onlineshop.service;

import kg.easyit.onlineshop.model.dto.UserDto;
import kg.easyit.onlineshop.model.entity.Account;
import kg.easyit.onlineshop.model.entity.Basket;
import kg.easyit.onlineshop.model.entity.User;
import kg.easyit.onlineshop.model.request.CreateAccountRequest;
import kg.easyit.onlineshop.model.request.CreateBasketRequest;
import kg.easyit.onlineshop.model.request.CreateUserRequest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public interface UserService extends UserDetailsService {
    UserDto create (CreateUserRequest createUserRequest);
    UserDto update (UserDto userDto);
    UserDto findById (Long id);
    UserDto delete(Long id);
    void setAccount(CreateAccountRequest request);
    void setBasket(CreateBasketRequest request);
    void save(User user);
    UserDto findByBasket(Basket basket);
}
