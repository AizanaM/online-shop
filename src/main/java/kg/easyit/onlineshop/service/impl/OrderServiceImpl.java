package kg.easyit.onlineshop.service.impl;

import kg.easyit.onlineshop.exceptions.OrderNotFoundException;
import kg.easyit.onlineshop.mapper.BasketMapper;
import kg.easyit.onlineshop.mapper.OrderMapper;
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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final BasketService basketService;
    private final ProductService productService;

    @Override
    public void save(OrderDto orderDto) {
        orderRepository.save(OrderMapper.INSTANCE.toEntity(orderDto));
    }

    @Override
    public OrderDto create(CreateOrderRequest request) {

        BasketDto basketDto = basketService.find(request.getBasketId());
        ProductDto productDto = productService.find(request.getProductId());

        if (request.getQuantityOfProducts() > productDto.getUnitsInStock()) {
            throw new RuntimeException("There are not enough " + productDto.getProductName() + "(s) in stock. " +
                                        "Number of units available: " + productDto.getUnitsInStock());
        }

        Order order = Order
                .builder()
                .basket(BasketMapper.INSTANCE.toEntity(basketDto))
                .product(ProductMapper.INSTANCE.toEntity(productDto))
                .quantityOfProducts(request.getQuantityOfProducts())
                .total(request.getTotal()) // (productDto.getPrice().multiply(new BigDecimal(request.getQuantityOfProducts())).round(new MathContext(2, RoundingMode.HALF_UP)))
                .build();

        return OrderMapper.INSTANCE.toDto(orderRepository.save(order));
    }

    @Override
    public List<OrderDto> createAll(List<CreateOrderRequest> request) {
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (CreateOrderRequest orderRequest : request) {
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
    public List<OrderDto> findByBasket(BasketDto basketDto) {
        List<Order> orders = orderRepository.findByBasket(BasketMapper.INSTANCE.toEntity(basketDto));
        return OrderMapper.INSTANCE.toDtoList(orders);
    }

//    @Override
//    public List<OrderDto> readOrdersByBasketId(GetOrdersByBasketIdRequest request) {
//        return OrderMapper.INSTANCE.toDtoList(orderRepository.findByBasketId(request.getBasketId()));
//    }

    @Override
    public OrderDto update(OrderDto orderDto) {
        return orderRepository.findById(orderDto.getId()).map(order -> {
//            order.setQuantityOfProducts(orderDto.getQuantityOfProducts());
            order.setOrderStatus(orderDto.getOrderStatus());
            orderRepository.save(order);

            return OrderMapper.INSTANCE.toDto(order);
        }).orElseThrow(() -> new OrderNotFoundException
                            ("Order with id=" + orderDto.getId() + " is not found"));
    }
//
//    @Override
//    public MessageResponse delete(Long id) {
//        orderRepository.deleteById(id);
//        return new MessageResponse("Order with id=" + id + " is deleted");
//        .orElseThrow(() -> new OrderNotFoundException("Order with id=" + id + " is not found."));
//    }

}
