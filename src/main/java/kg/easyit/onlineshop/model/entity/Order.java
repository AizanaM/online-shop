package kg.easyit.onlineshop.model.entity;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;
import kg.easyit.onlineshop.model.enums.OrderStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "tb_order")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order extends AbstractPersistable<Long> {
    @ManyToOne
    @JoinColumn(name = "basket_id", referencedColumnName = "id")
    Basket basket;

    @CreationTimestamp
    Date dateCreated;

    @UpdateTimestamp
    Date dateUpdated;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    Product product;

    @Column(name = "quantity_of_products", nullable = false)
    Integer quantityOfProducts;

    @Column(name = "total", nullable = false)
    BigDecimal total;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    OrderStatus orderStatus;
}
