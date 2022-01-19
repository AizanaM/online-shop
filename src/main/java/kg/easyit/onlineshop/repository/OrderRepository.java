package kg.easyit.onlineshop.repository;

import kg.easyit.onlineshop.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT o.* FROM tb_order AS o WHERE o.basket_id = ?1", nativeQuery = true)
    List<Order> findOrdersByBasketId(Long basketId);

    @Query(value = "SELECT od.* " +
            "FROM tb_order " +
            "AS od WHERE od.id = ?1 " +
            "AND od.order_status = 'ACTIVE' " +
            "OR od.order_status = 'COMPLETED'" , nativeQuery = true)
    Optional<Order> findOrderByIdAndOrderStatusActiveOrCompleted(Long id);

}
