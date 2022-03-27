package com.yunus.fakebank.Service;

import com.yunus.fakebank.Entity.Account;
import com.yunus.fakebank.Repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class AccountService {

    public AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // iban will be random long and it must be unique
    public void addAccount(Account account) {
        Random randomGenerator = new Random();
        Long iban = Math.abs(randomGenerator.nextLong());
        Optional<Account> accountByIban = accountRepository.findAccountByIban(iban);
        while (accountByIban.isPresent()) {
            iban = Math.abs(randomGenerator.nextLong());
            accountByIban = accountRepository.findAccountByIban(iban);
        }
        account.setIban(iban);
        accountRepository.save(account);

    }

    public void updateAccount(Long id, int balance) {
        Account account1 = accountRepository.findById(id).orElseThrow(() -> new IllegalStateException("ERROR: account cannot found on update"));
        account1.setBalance(balance);
        accountRepository.save(account1);
    }

    public void deleteAccount(Long account_id) {
        if (accountRepository.existsById(account_id)) {
            accountRepository.deleteById(account_id);
        } else {
            throw new IllegalStateException("ERROR: account not found on delete");
        }
    }

    public void deleteAccountsBySsn(Long ssn) {
//        Optional<Account> accountBySsn = accountRepository.findAccountBySsn(ssn);
        Optional<List<Account>> accountsBySsn = accountRepository.findAccountsBySsn(ssn);
        if (accountsBySsn.isPresent()) {
            accountRepository.deleteAccountsBySsn(ssn);
        } else {
            throw new IllegalStateException("Account not found from ssn");
        }

    }

    public List<Account> getAccountBySsn(Long ssn) {
        return accountRepository.findAccountsBySsnEquals(ssn);
    }

    public Account getAccountByAccount_id(Long account_id) {
        return accountRepository.getAccountByAccount_id(account_id);
    }

    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }
}
