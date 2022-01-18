package kg.easyit.onlineshop.mapper;

import kg.easyit.onlineshop.model.dto.RoleDto;
import kg.easyit.onlineshop.model.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper extends BaseMapper<Role, RoleDto> {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);
}
