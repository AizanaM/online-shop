package kg.easyit.onlineshop.service;

import kg.easyit.onlineshop.model.dto.OrderDto;
import kg.easyit.onlineshop.model.request.CreateOrderRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    void save(OrderDto orderDto);

    OrderDto create(CreateOrderRequest request);

    List<OrderDto> createAll(List<CreateOrderRequest> request);

    OrderDto find(Long id);

//    List<OrderDto> readOrdersByBasketId(GetOrdersByBasketIdRequest request);

    List<OrderDto> findByBasket(Long basketId);

    OrderDto updateProductQuantity(OrderDto orderDto);

    OrderDto cancel(Long id);

    List<OrderDto> cancelAll(List<Long> ids);

//    MessageResponse delete(Long id);

}
