package kg.easyit.onlineshop.service;

import kg.easyit.onlineshop.model.dto.BasketDto;
import kg.easyit.onlineshop.model.response.MessageResponse;
import org.springframework.stereotype.Service;

@Service
public interface BasketService {

    BasketDto create(BasketDto basketDto);

    BasketDto find(Long id);

    BasketDto update(BasketDto basketDto);

    MessageResponse delete(Long id);

}
