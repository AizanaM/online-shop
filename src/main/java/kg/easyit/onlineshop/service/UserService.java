package kg.easyit.onlineshop.service;

import kg.easyit.onlineshop.model.dto.UserDto;
import kg.easyit.onlineshop.model.request.CreateUserRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDto create (CreateUserRequest createUserRequest);
    UserDto update (UserDto userDto);

}
