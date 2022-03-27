package com.yunus.fakebank.Repository;

import com.yunus.fakebank.Entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findLoansByAccountId(Long account_id);

    @Query(value = "SELECT balance FROM account WHERE account_id = ?1", nativeQuery = true)
    int findBalanceByAccountId(Long accountId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE account SET balance = ?2  WHERE account_id = ?1", nativeQuery = true)
    void setBalanceByAccountId(Long accountId, int amount);

    @Transactional
    void deleteLoansByAccountId(Long accountId);
}
