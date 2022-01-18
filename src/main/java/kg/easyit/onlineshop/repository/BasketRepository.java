package kg.easyit.onlineshop.repository;

import com.sun.org.apache.xpath.internal.objects.XBoolean;
import kg.easyit.onlineshop.model.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {

    Boolean existsByUserIdAndAndIsActiveTrue(Long userId);
    Optional<Basket> findBasketByUserIdAndIsActiveTrue(Long userId);

}
