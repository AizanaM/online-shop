package kg.easyit.onlineshop.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Basket")
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Basket extends AbstractPersistable<Long> {

    @CreationTimestamp
    @Column(name = "date_created", nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    Date dateCreated;

    @UpdateTimestamp
    @Column(name = "date_updated", nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    Date dateUpdated;

//    @Column(name = "is_active", nullable = false)
//    Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    User user;

    @OneToMany
    @JoinColumn(name = "order_id", nullable = false, referencedColumnName = "id")
    List<Order> orders;

    @Column(name = "total_sum", nullable = false)
    BigDecimal totalSum;

}