package kg.easyit.onlineshop.service;

import kg.easyit.onlineshop.model.dto.BasketDto;
import kg.easyit.onlineshop.model.request.CreateBasketRequest;
import kg.easyit.onlineshop.model.response.MessageResponse;
import org.springframework.stereotype.Service;

@Service
public interface BasketService {

    BasketDto create(CreateBasketRequest request);

    BasketDto find(Long id);

    MessageResponse clearBasket(BasketDto basketDto);

    BasketDto update(BasketDto basketDto);

    MessageResponse delete(Long id);

}
