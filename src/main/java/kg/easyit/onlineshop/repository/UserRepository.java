package kg.easyit.onlineshop.repository;

import kg.easyit.onlineshop.model.entity.Basket;
import kg.easyit.onlineshop.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
    Optional<User> findByIdAndIsActiveTrue(Long id);

    Optional<User> findByIdAndIsActiveTrue(Long id);
    Optional<User> findByBasket(Basket basket);

}
