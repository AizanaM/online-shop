package kg.easyit.onlineshop.service.impl;

import kg.easyit.onlineshop.exceptions.BasketNotFoundException;
import kg.easyit.onlineshop.mapper.BasketMapper;
import kg.easyit.onlineshop.mapper.OrderMapper;
import kg.easyit.onlineshop.mapper.UserMapper;
import kg.easyit.onlineshop.model.dto.BasketDto;
import kg.easyit.onlineshop.model.dto.UserDto;
import kg.easyit.onlineshop.model.entity.Basket;
import kg.easyit.onlineshop.model.request.CreateBasketRequest;
import kg.easyit.onlineshop.repository.BasketRepository;
import kg.easyit.onlineshop.service.BasketService;
import kg.easyit.onlineshop.service.OrderService;
import kg.easyit.onlineshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final UserService userService;
    private final OrderService orderService;

    @Autowired
    public BasketServiceImpl(BasketRepository basketRepository,@Lazy UserService userService,@Lazy OrderService orderService) {
        this.basketRepository = basketRepository;
        this.userService = userService;
        this.orderService = orderService;
    }

    @Override
    public BasketDto create(CreateBasketRequest request) {

        UserDto userDto = userService.findById(request.getUserId());

        Basket basket = Basket
                .builder()
                .user(UserMapper.INSTANCE.toEntity(userDto))
                .totalSum(request.getTotalSum())
                .build();

        return BasketMapper.INSTANCE.toDto(basketRepository.save(basket));
    }

    @Override
    public BasketDto find(Long id) {
        Basket basket = basketRepository
                .findById(id)
                .orElseThrow(() -> new BasketNotFoundException("Basket with id=" + id + " is not found"));

        return BasketMapper.INSTANCE.toDto(basket);
    }

    @Override
    public List<BasketDto> findByUser(Long userId) {
        List<Basket> baskets = basketRepository.findBasketsByUserId(userId);
        return BasketMapper.INSTANCE.toDtoList(baskets);
    }

    @Override
    public void save(Basket basket) {
        basketRepository.save(basket);
    }

//    @Override
//    public MessageResponse clearBasket(BasketDto basketDto) {
//        List<Order> orders = OrderMapper.INSTANCE.toEntityList(orderService.findByBasket(basketDto));
//        for (Order order : orders) {
//            order.setOrderStatus(OrderStatus.CANCELED);
//            orderService.save(OrderMapper.INSTANCE.toDto(order));
//        }
//        return new MessageResponse("The basket has been cleared");
//    }

//    @Override
//    public BasketDto update(BasketDto basketDto) {
////        Change list of orders ?
//        return null;
//    }
//
//    @Override
//    public MessageResponse delete(Long id) {
////        After basket's totalSum is paid, set isActive false (Boolean paid true ?)
//        return null;
//    }

}
