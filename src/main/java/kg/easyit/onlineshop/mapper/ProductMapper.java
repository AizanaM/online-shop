package kg.easyit.onlineshop.mapper;

import kg.easyit.onlineshop.model.dto.ProductDto;
import kg.easyit.onlineshop.model.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper extends BaseMapper<Product, ProductDto>{
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
}
