package kg.easyit.onlineshop.service.impl;

import kg.easyit.onlineshop.exceptions.UserNotFoundException;
import kg.easyit.onlineshop.mapper.UserMapper;
import kg.easyit.onlineshop.model.dto.AccountDto;
import kg.easyit.onlineshop.model.dto.BasketDto;
import kg.easyit.onlineshop.model.dto.UserDto;
import kg.easyit.onlineshop.model.entity.Account;
import kg.easyit.onlineshop.model.entity.Basket;
import kg.easyit.onlineshop.model.entity.User;
import kg.easyit.onlineshop.model.request.CreateUserRequest;
import kg.easyit.onlineshop.repository.UserRepository;
import kg.easyit.onlineshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    // private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto create(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email: " + request.getEmail() + " already in use");
        }
        if (request.getUsername() == null || request.getUsername().trim().equals("")) {
            request.setUsername(request.getEmail().substring(0, request.getEmail().indexOf('@')));
        }
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username: " + request.getUsername() + " already in use");
        }
        User user = User
                .builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .username(request.getUsername())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(request.getPassword())
                .build();

        UserDto userDto = UserMapper.INSTANCE.toDto(user);

                Account account = Account
                        .builder()
                        .accountName("Default name")
                        .availableMoney(BigDecimal.ZERO)
                        .isActive(true)
                        .user(user)
                        .build();


                Basket basket = Basket
                        .builder()
                        .isActive(true)
                        .user(user)
                        .build();

        userRepository.save(user);

        return userDto;
    }

    @Override
    public UserDto delete(Long id) {
        return UserMapper.INSTANCE.toDto(userRepository.findByIdAndIsActiveTrue(id).map(user -> {
            user.setIsActive(false);
            return userRepository.save(user);
        }).orElseThrow(() -> new UserNotFoundException("User not found or already deleted")));
    }
  
    @Override
    public UserDto findByBasket(Basket basket) {
        return null;
    }

    @Override
    public UserDto update(UserDto userDto) {
        User userEntity = userRepository.findByIdAndIsActiveTrue(userDto.getId()).map(user -> {
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setUsername(userDto.getUsername());
            user.setEmail(userDto.getEmail());
            user.setPhoneNumber(userDto.getPhoneNumber());
            return userRepository.save(user);
        }).orElseThrow(()-> new RuntimeException("Not found"));

        return UserMapper.INSTANCE.toDto(userEntity);
    }

    @Override
    public UserDto findById(Long id) {
        return UserMapper.INSTANCE
                .toDto(userRepository
                        .findByIdAndIsActiveTrue(id)
                        .orElseThrow(() -> new UserNotFoundException("User with id" + id + "is not found")));
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Username" + username + "is not found"));
    }
}
