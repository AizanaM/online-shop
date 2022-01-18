package kg.easyit.onlineshop.model.entity;

import kg.easyit.onlineshop.model.enums.OrderStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Order")
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order extends AbstractPersistable<Long> {

    @CreationTimestamp
    @Column(name = "date_created", nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    Date dateCreated;

    @UpdateTimestamp
    @Column(name = "date_updated", nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    Date dateUpdated;

    @ManyToOne
    @JoinColumn(name = "basket_id", nullable = false, referencedColumnName = "id")
    Basket basket;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false, referencedColumnName = "id")
    Product product;

    @Column(name = "quantity_of_products", nullable = false)
    Integer quantityOfProducts;

    @Column(name = "total", nullable = false)
    BigDecimal total;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    OrderStatus orderStatus;
}
