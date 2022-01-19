package kg.easyit.onlineshop.repository;

import kg.easyit.onlineshop.model.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {

//    Boolean existsByUserIdAndAndIsActiveTrue(Long userId);
//    Optional<Basket> findBasketByUserIdAndIsActiveTrue(Long userId);

    @Query(value = "SELECT b.* FROM tb_basket AS b WHERE b.order_id = ?1", nativeQuery = true)
    List<Basket> findBasketsByUserId(Long userId); // ?

}
