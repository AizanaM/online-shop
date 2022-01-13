package kg.easyit.onlineshop.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Basket")
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Basket extends AbstractPersistable<Long> {

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;
    Order order;
    Double TotalSum;
}