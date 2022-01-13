package kg.easyit.onlineshop.model.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "account")
public class Account extends AbstractPersistable<Long> {


    @Column(table = "account_name", nullable = false)
    String accountName;

    @OneToMany
    @JoinColumn(name = "transaction_id", referencedColumnName = "id")
    List<Transaction> transactions;


}
