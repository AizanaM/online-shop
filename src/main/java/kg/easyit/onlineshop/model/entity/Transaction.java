package kg.easyit.onlineshop.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "transaction")
public class
Transaction extends AbstractPersistable<Long> {

    @Column(name = "purpose", nullable = false)
    String purpose;

    @Column(name = "amount", nullable = false)
    BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "account_to_id", referencedColumnName = "id")
    Account accountToId;

    @ManyToOne
    @JoinColumn(name = "account_from_id", referencedColumnName = "id")
    Account accountFromId;
}
