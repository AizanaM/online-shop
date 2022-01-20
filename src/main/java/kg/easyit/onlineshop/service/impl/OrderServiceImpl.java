package kg.easyit.onlineshop.service.impl;

import kg.easyit.onlineshop.exceptions.OrderNotFoundException;
import kg.easyit.onlineshop.mapper.BasketMapper;
import kg.easyit.onlineshop.mapper.OrderMapper;
import kg.easyit.onlineshop.mapper.ProductMapper;
import kg.easyit.onlineshop.model.dto.BasketDto;
import kg.easyit.onlineshop.model.dto.OrderDto;
import kg.easyit.onlineshop.model.dto.ProductDto;
import kg.easyit.onlineshop.model.entity.Order;
import kg.easyit.onlineshop.model.enums.OrderStatus;
import kg.easyit.onlineshop.model.request.CreateOrderRequest;
import kg.easyit.onlineshop.repository.OrderRepository;
import kg.easyit.onlineshop.service.BasketService;
import kg.easyit.onlineshop.service.OrderService;
import kg.easyit.onlineshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final BasketService basketService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            @Lazy BasketService basketService) {
        this.orderRepository = orderRepository;
        this.basketService = basketService;
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public OrderDto create(CreateOrderRequest request) {
        basketService.setOrder(request);
        return OrderDto.builder()
                .build();
    }

    @Override
    public List<OrderDto> createAll(List<CreateOrderRequest> requests) {
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (CreateOrderRequest orderRequest : requests) {
            orderDtoList.add(create(orderRequest));
        }
        return orderDtoList;
    }

    @Override
    public OrderDto find(Long id) {
        Order order = orderRepository
                .findById(id)
                .orElseThrow(() -> new OrderNotFoundException
                        ("Order with id=" + id + " is not found"));

        return OrderMapper.INSTANCE.toDto(order);
    }

    @Override
    public List<OrderDto> findByBasket(Long basketId) {
        List<Order> orders = orderRepository.findOrdersByBasketId(basketId);
        return OrderMapper.INSTANCE.toDtoList(orders);
    }

//    @Override
//    public List<OrderDto> readOrdersByBasketId(GetOrdersByBasketIdRequest request) {
//        return OrderMapper.INSTANCE.toDtoList(orderRepository.findByBasketId(request.getBasketId()));
//    }

    @Override
    public OrderDto updateProductQuantity(OrderDto orderDto) {
        return orderRepository.findById(orderDto.getId()).map(order -> {
            order.setQuantityOfProducts(orderDto.getQuantityOfProducts());
            orderRepository.save(order);

            return OrderMapper.INSTANCE.toDto(order);
        }).orElseThrow(() -> new OrderNotFoundException
                ("Order with id=" + orderDto.getId() + " is not found"));
    }

    @Override
    public OrderDto cancel(Long id) {
        return OrderMapper.INSTANCE.toDto(orderRepository.findOrderByIdAndOrderStatusActiveOrCompleted(id)
                .map(order -> {
                    if (order.getOrderStatus().equals(OrderStatus.COMPLETED)) {
                        throw new RuntimeException("The order is completed. You can return it.");
                    }
                    order.setOrderStatus(OrderStatus.CANCELED);
                    return orderRepository.save(order);
                }).orElseThrow(() -> new RuntimeException("The order is cancelled.")));
    }

    @Override
    public List<OrderDto> cancelAll(List<Long> ids) {
        for (Long id : ids) {
            cancel(id);
        }
        return OrderMapper.INSTANCE.toDtoList(orderRepository.findAll()
                .stream()
                .filter(order -> ids.contains(order.getId()))
                .collect(Collectors.toList()));
    }

//
//    @Override
//    public MessageResponse delete(Long id) {
//        orderRepository.deleteById(id);
//        return new MessageResponse("Order with id=" + id + " is deleted");
//        .orElseThrow(() -> new OrderNotFoundException("Order with id=" + id + " is not found."));
//    }

}
