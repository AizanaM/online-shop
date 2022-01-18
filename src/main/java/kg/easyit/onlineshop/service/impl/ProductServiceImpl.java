package kg.easyit.onlineshop.service.impl;

import kg.easyit.onlineshop.mapper.ProductMapper;
import kg.easyit.onlineshop.model.dto.ProductDto;
import kg.easyit.onlineshop.model.entity.Product;
import kg.easyit.onlineshop.model.request.CreateProductRequest;
import kg.easyit.onlineshop.repository.ProductRepository;
import kg.easyit.onlineshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductDto create(CreateProductRequest request) {
        if (productRepository.existsByProductName(request.getProductName())) {
            throw new RuntimeException("Product: " + request.getProductName() + " already exists");
        }
        Product product = Product
                .builder()
                .productName(request.getProductName())
                .price(request.getPrice())
                .unitsInStock(request.getUnitsInStock())
                .build();
        ProductDto productDto = ProductMapper.INSTANCE.toDto(product);
        productRepository.save(product);

        return productDto;
    }

    @Override
    public ProductDto findById(Long id) {
        return ProductMapper.INSTANCE
                .toDto(productRepository
                .findByIdAndIsActiveTrue(id)
                        .orElseThrow(() -> new RuntimeException("Product not found")));

    }
    @Override
    public ProductDto update(ProductDto productDto) {
        Product product = productRepository.findByIdAndIsActiveTrue(productDto.getId())
                .map(product1 -> {
                    product1.setProductName(productDto.getProductName());
                    product1.setPrice(productDto.getPrice());
                    product1.setIsActive(productDto.getIsActive());
                    product1.setUnitsInStock(productDto.getUnitsInStock());
                    return productRepository.save(product1);
                }).orElseThrow(()-> new RuntimeException("Not found"));

        return ProductMapper.INSTANCE.toDto(product);
    }


}
