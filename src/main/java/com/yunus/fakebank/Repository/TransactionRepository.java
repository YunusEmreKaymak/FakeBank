package com.yunus.fakebank.Repository;

import com.yunus.fakebank.Entity.Transaction;
import com.yunus.fakebank.Entity.TransactionPKId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, TransactionPKId> {

    List<Transaction> getTransactionsByGivenId(Long idGiven);

    List<Transaction> getTransactionsByReceivenId(Long idReceiven);

    @Transactional
    @Modifying
    @Query(value = "UPDATE account SET balance =(balance + ?1) WHERE account_id = ?2", nativeQuery = true)
    void increaseAmountFromBalance(int amount, Long receivenId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE account SET balance =(balance - ?1) WHERE account_id = ?2", nativeQuery = true)
    void decreaseAmountFromBalance(int amount, Long givenId);


    @Transactional
    @Query(value = "SELECT account_type FROM account WHERE account_id = ?1", nativeQuery = true)
    String getAccountTypeFromAccountId(Long accountId);

    @Transactional
    @Query(value = "SELECT EXISTS (SELECT account_type FROM account WHERE account_id = ?1)", nativeQuery = true)
    String existAccountTypeFromAccountId(Long accountId);


    @Transactional
    void deleteTransactionsByReceivenId(Long receivenId);

    @Transactional
    void deleteTransactionsByGivenId(Long givenId);
}
