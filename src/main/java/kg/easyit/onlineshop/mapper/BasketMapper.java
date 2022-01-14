package kg.easyit.onlineshop.mapper;

import kg.easyit.onlineshop.model.dto.BasketDto;
import kg.easyit.onlineshop.model.entity.Basket;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BasketMapper extends BaseMapper<Basket, BasketDto> {

    BasketMapper INSTANCE = Mappers.getMapper(BasketMapper.class);

}
