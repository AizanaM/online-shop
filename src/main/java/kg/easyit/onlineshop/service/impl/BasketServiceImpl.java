package kg.easyit.onlineshop.service.impl;

import kg.easyit.onlineshop.exceptions.BasketNotFoundException;
import kg.easyit.onlineshop.mapper.BasketMapper;
import kg.easyit.onlineshop.mapper.OrderMapper;
import kg.easyit.onlineshop.mapper.UserMapper;
import kg.easyit.onlineshop.model.dto.BasketDto;
import kg.easyit.onlineshop.model.dto.OrderDto;
import kg.easyit.onlineshop.model.dto.UserDto;
import kg.easyit.onlineshop.model.entity.Basket;
import kg.easyit.onlineshop.model.entity.Order;
import kg.easyit.onlineshop.model.entity.Product;
import kg.easyit.onlineshop.model.entity.User;
import kg.easyit.onlineshop.model.enums.OrderStatus;
import kg.easyit.onlineshop.model.request.CreateBasketRequest;
import kg.easyit.onlineshop.model.request.CreateOrderRequest;
import kg.easyit.onlineshop.repository.BasketRepository;
import kg.easyit.onlineshop.service.BasketService;
import kg.easyit.onlineshop.service.OrderService;
import kg.easyit.onlineshop.service.ProductService;
import kg.easyit.onlineshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final OrderService orderService;
    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public BasketServiceImpl(BasketRepository basketRepository,
                             OrderService orderService,
                             ProductService productService,
                             UserService userService) {
        this.basketRepository = basketRepository;
        this.orderService = orderService;
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public BasketDto create(CreateBasketRequest request) {
        userService.setBasket(request);
        return BasketDto.builder().build();
    }

    @Override
    public void setOrder(CreateOrderRequest request) {
        Order order = Order.builder()
                .orderStatus(OrderStatus.ACTIVE)
                .quantityOfProducts(request.getQuantityOfProducts())
                .total(request.getTotal())
                .build();
        Basket basket = basketRepository.getById(request.getBasketId());
        Product product = productService.getById(request.getProductId());
        order.setBasket(basket);
        order.setProduct(product);
        orderService.save(order);
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
