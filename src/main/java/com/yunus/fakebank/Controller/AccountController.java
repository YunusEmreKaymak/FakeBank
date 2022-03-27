package com.yunus.fakebank.Controller;

import com.yunus.fakebank.Entity.Account;
import com.yunus.fakebank.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@CrossOrigin("*")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<Account> getAccounts() {
        return accountService.getAccounts();
    }

    @GetMapping("/{ssn}")
    public List<Account> getAccountsBySsn(@PathVariable("ssn") Long ssn) {
        return accountService.getAccountBySsn(ssn);
    }

    @GetMapping("/a/{account_id}")
    public Account getAccountByAccount_id(@PathVariable("account_id") Long account_id) {
        Account accountByAccount_id = accountService.getAccountByAccount_id(account_id);
        System.out.println(accountByAccount_id.getAccountType());
        return accountService.getAccountByAccount_id(account_id);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable("id") Long id) {
        accountService.deleteAccount(id);
    }

    @DeleteMapping("/ssn/{ssn}")
    public void deleteAccountsBySsn(@PathVariable("ssn") Long ssn) {
        accountService.deleteAccountsBySsn(ssn);
    }

    @PostMapping
    public void addAccount(@RequestBody Account account) {
        accountService.addAccount(account);
    }

    @PutMapping("/{id}/{balance}")
    public void updateAccount(@PathVariable("id") Long id, @PathVariable("balance") int balance) {
        accountService.updateAccount(id, balance);
    }


}
