package kg.easyit.onlineshop.service.impl;

import kg.easyit.onlineshop.model.dto.BasketDto;
import kg.easyit.onlineshop.model.response.MessageResponse;
import kg.easyit.onlineshop.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

    @Override
    public BasketDto create(BasketDto basketDto) {
        return null;
    }

    @Override
    public BasketDto find(Long id) {
        return null;
    }

    @Override
    public BasketDto update(BasketDto basketDto) {
        return null;
    }

    @Override
    public MessageResponse delete(Long id) {
        return null;
    }

}
