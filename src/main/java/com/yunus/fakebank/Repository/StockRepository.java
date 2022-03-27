package com.yunus.fakebank.Repository;

import com.yunus.fakebank.Entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    List<Stock> findStocksByAccountId(Long id);

    @Query(value = "SELECT balance FROM account WHERE account_id = ?1", nativeQuery = true)
    int getBalance(Long accountId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE account SET balance = ?1 WHERE account_id = ?2", nativeQuery = true)
    void decreaseAmountFromBalance(int newBalance, Long accountId);

    @Transactional
    void deleteStocksByAccountId(Long accountId);


}
