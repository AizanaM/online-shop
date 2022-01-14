package kg.easyit.onlineshop.mapper;

import kg.easyit.onlineshop.model.dto.OrderDto;
import kg.easyit.onlineshop.model.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper extends BaseMapper<Order, OrderDto> {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

}
