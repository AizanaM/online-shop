package kg.easyit.onlineshop.model.entity;

import kg.easyit.onlineshop.model.enums.Authority;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_role")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role extends AbstractPersistable<Long> {

    @Column(name = "role_name", nullable = false, unique = true)
    String roleName;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "role_has_authorities", joinColumns = @JoinColumn(name = "role_id"))
    @Column(name = "authorities_id", nullable = false)
    List<Authority> authorities;

}
