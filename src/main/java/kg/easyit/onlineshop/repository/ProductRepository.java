package kg.easyit.onlineshop.repository;

import kg.easyit.onlineshop.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByProductName(String productName);

    Optional<Product> findByIdAndIsActiveTrue(Long id);
}
