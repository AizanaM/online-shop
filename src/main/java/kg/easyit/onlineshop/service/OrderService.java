package kg.easyit.onlineshop.service;

import kg.easyit.onlineshop.model.dto.BasketDto;
import kg.easyit.onlineshop.model.dto.OrderDto;
import kg.easyit.onlineshop.model.request.CreateOrderRequest;
import kg.easyit.onlineshop.model.request.GetOrdersByBasketIdRequest;
import kg.easyit.onlineshop.model.response.MessageResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    void save(OrderDto orderDto);

    OrderDto create(CreateOrderRequest request);

    List<OrderDto> createAll(List<CreateOrderRequest> request);

    OrderDto find(Long id);

    List<OrderDto> readOrdersByBasketId(GetOrdersByBasketIdRequest request);

    List<OrderDto> findByBasket(BasketDto basketDto);

    OrderDto update(OrderDto orderDto);

    MessageResponse delete(Long id);

}
