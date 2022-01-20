package kg.easyit.onlineshop.service;

import kg.easyit.onlineshop.model.dto.BasketDto;
import kg.easyit.onlineshop.model.dto.OrderDto;
import kg.easyit.onlineshop.model.entity.Basket;
import kg.easyit.onlineshop.model.request.CreateBasketRequest;
import kg.easyit.onlineshop.model.request.CreateOrderRequest;
import kg.easyit.onlineshop.model.response.MessageResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BasketService {
    BasketDto create(CreateBasketRequest request);
    void setOrder(CreateOrderRequest request);
    BasketDto find(Long id);
    List<BasketDto> findByUser(Long userId);
    void save(Basket basket);
//    MessageResponse clearBasket(BasketDto basketDto);
//    BasketDto update(BasketDto basketDto);
//    MessageResponse delete(Long id);
}
