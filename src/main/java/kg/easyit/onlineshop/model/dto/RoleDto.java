package kg.easyit.onlineshop.model.dto;

import kg.easyit.onlineshop.model.enums.Authority;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleDto {

    Long id;
    String roleName;
    List<Authority> authorities;

}
