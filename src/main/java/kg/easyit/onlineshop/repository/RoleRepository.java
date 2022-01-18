package kg.easyit.onlineshop.repository;

import kg.easyit.onlineshop.model.entity.Role;
import kg.easyit.onlineshop.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    boolean existsByRoleName(String roleName);
    Optional<Role> findByIdAndIsActiveTrue(Long id);

}
