package kg.easyit.onlineshop.service;

import kg.easyit.onlineshop.model.dto.UserDto;
import kg.easyit.onlineshop.model.entity.Basket;
import kg.easyit.onlineshop.model.entity.User;
import kg.easyit.onlineshop.model.request.CreateUserRequest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {
    UserDto create (CreateUserRequest createUserRequest);
    UserDto update (UserDto userDto);
    UserDto findById (Long id);
    UserDto delete(Long id);
    void save(User user);
    UserDto findByBasket(Basket basket);
}
