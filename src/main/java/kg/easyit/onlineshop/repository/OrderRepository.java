package kg.easyit.onlineshop.repository;

import kg.easyit.onlineshop.model.entity.Basket;
import kg.easyit.onlineshop.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByBasket(Basket basket);

}
