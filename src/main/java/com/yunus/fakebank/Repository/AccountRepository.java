package com.yunus.fakebank.Repository;

import com.yunus.fakebank.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findAccountByIban(Long iban);

    List<Account> findAccountsBySsnEquals(Long ssn);

    @Query(value = "SELECT * FROM account WHERE account_id= ?1", nativeQuery = true)
    Account getAccountByAccount_id(Long account_id);

    @Transactional
    @Modifying
    @Query(value = "delete from account where ssn = ?1", nativeQuery = true)
    void deleteAccountsBySsn(Long ssn);

    Optional<Account> findAccountBySsn(Long ssn);

    Optional<List<Account>> findAccountsBySsn(Long ssn);


}
