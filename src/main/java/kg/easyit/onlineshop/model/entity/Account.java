package kg.easyit.onlineshop.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tb_account")
public class Account extends AbstractPersistable<Long> {


    @Column(name = "account_name", nullable = false)
    String accountName;

    @Column(name = "available_money", nullable = false)
    BigDecimal availableMoney;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    User user;

    @OneToMany
    @JoinColumn(name = "transaction_id", referencedColumnName = "id")
    List<Transaction> transactions;

    @Column(name = "is_Active", nullable = false, columnDefinition = "Boolean default true")
    Boolean isActive;


}
