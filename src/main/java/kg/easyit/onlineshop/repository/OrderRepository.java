package kg.easyit.onlineshop.repository;

import kg.easyit.onlineshop.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o.* FROM tb_order AS o WHERE basket_id = ?1")
    List<Order> findOrdersByBasketId(Long basketId);

    Optional<Order> findOrderByIdAndOrderStatusActiveAndOrderStatusCompleted(Long id);

}
