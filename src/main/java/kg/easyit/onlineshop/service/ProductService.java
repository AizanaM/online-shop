package kg.easyit.onlineshop.service;

import kg.easyit.onlineshop.model.dto.ProductDto;
import kg.easyit.onlineshop.model.request.CreateProductRequest;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    ProductDto create (CreateProductRequest request);
    ProductDto update (ProductDto productDto);
    ProductDto findById (Long id);

}
