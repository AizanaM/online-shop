package kg.easyit.onlineshop.repository;

import kg.easyit.onlineshop.model.entity.Account;
import kg.easyit.onlineshop.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<Transaction> findById(Long id);

    @Query(value ="SELECT * FROM tb_transaction WHERE account_from_id = ?1", nativeQuery = true)
    Optional<List<Transaction>> findByAccountFromId(Long accountId);

    @Query(value ="SELECT * FROM tb_transaction WHERE account_to_id = ?1", nativeQuery = true)
    Optional<List<Transaction>> findByAccountToId(Long accountId);



}
