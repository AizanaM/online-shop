package kg.easyit.onlineshop.model.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Cascade;
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
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "account_name", nullable = false)
    String accountName;

    @Column(name = "available_money", nullable = false)
    BigDecimal availableMoney;

//    @ManyToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    User user;

    @OneToMany
    List<Transaction> transactions;

    @Column(name = "is_active", nullable = false)
    Boolean isActive;
}
