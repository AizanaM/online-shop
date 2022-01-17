package kg.easyit.onlineshop.repository;

import kg.easyit.onlineshop.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    boolean existsByRoleName(String roleName);

}
