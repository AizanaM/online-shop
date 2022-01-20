package kg.easyit.onlineshop.mapper;

import kg.easyit.onlineshop.model.dto.UserDto;
import kg.easyit.onlineshop.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper extends BaseMapper<User, UserDto> {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
}
