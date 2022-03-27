package com.yunus.fakebank.Controller;

import com.yunus.fakebank.Controller.AccountController;
import com.yunus.fakebank.Entity.Account;
import com.yunus.fakebank.Entity.Customer;
import com.yunus.fakebank.Controller.CustomerController;
import com.yunus.fakebank.Service.AccountService;
import com.yunus.fakebank.Service.CustomerService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountControllerTest {
    @Autowired
    AccountService accountService;
    @Autowired
    CustomerService customerService;


    @Test
    void getAccountsBySsn() {
        AccountController controllerAccount = new AccountController(accountService);
        CustomerController controllerCustomer = new CustomerController(customerService);
        // Created customer spesific for this account
        Customer customer = new Customer(9999999L, "asd999@gmail.com", "999999999", "John Doe", "asdasd", 4300);
        controllerCustomer.registerNewCustomer(customer);
        Account account = new Account( 9999999L ,"DOLLAR");
        controllerAccount.addAccount(account);
//        Set<Account> accounts = new HashSet<>();
        List<Account> accounts = new ArrayList<>();
        accounts.add(account);
        List<Account> accountsBySsn = controllerAccount.getAccountsBySsn(9999999L);
        for (int i = 0; i < accounts.size() ; i++){
            Set<Account> accountSet = new HashSet<>();
            accountSet.add(accounts.get(i));
            accountSet.add(accountsBySsn.get(i));
            assertEquals(1,accounts.size());
        }
        controllerAccount.deleteAccountsBySsn(9999999L);
        controllerCustomer.deleteCustomer(9999999L);

    }

    @Test
    void getAccountsByAccount_id() {
        AccountController controllerAccount = new AccountController(accountService);
        CustomerController controllerCustomer = new CustomerController(customerService);
        // Created customer spesific for this account
        Customer customer = new Customer(9999999L, "asd999@gmail.com", "999999999", "John Doe", "asdasd", 4300);
        controllerCustomer.registerNewCustomer(customer);
        // Created account
        Account account = new Account( 9999999L ,"DOLLAR");
        controllerAccount.addAccount(account);

        Long account_id = controllerAccount.getAccountsBySsn(9999999L).get(0).getAccount_id();
        account.setAccount_id(account_id);

        Set<Account> accounts = new HashSet<>();
        accounts.add(controllerAccount.getAccountByAccount_id(account_id));
        accounts.add(account);


        assertEquals(1,accounts.size());

        controllerAccount.deleteAccountsBySsn(9999999L);
        controllerCustomer.deleteCustomer(9999999L);
    }

    @Test
    void deleteAccount() {
        AccountController controllerAccount = new AccountController(accountService);
        CustomerController controllerCustomer = new CustomerController(customerService);
        // Created customer spesific for this account
        Customer customer = new Customer(9999999L, "asd999@gmail.com", "999999999", "John Doe", "asdasd", 4300);
        controllerCustomer.registerNewCustomer(customer);
        // Created account
        Account account = new Account( 9999999L ,"DOLLAR");
        controllerAccount.addAccount(account);

        Long account_id = controllerAccount.getAccountsBySsn(9999999L).get(0).getAccount_id();
        controllerAccount.deleteAccount(account_id);
        assertThrows(new IllegalStateException().getClass(),() -> controllerAccount.deleteAccount(account_id));

        controllerCustomer.deleteCustomer(9999999L);
    }

    @Test
    void addAccount() {
        AccountController controllerAccount = new AccountController(accountService);
        CustomerController controllerCustomer = new CustomerController(customerService);
        // Created customer spesific for this account
        Customer customer = new Customer(9999999L, "asd999@gmail.com", "999999999", "John Doe", "asdasd", 4300);
        controllerCustomer.registerNewCustomer(customer);
        // Created account
        Account account = new Account( 9999999L ,"DOLLAR");
        controllerAccount.addAccount(account);
        // Getting account_id from database and writing to the object
        Long account_id = controllerAccount.getAccountsBySsn(9999999L).get(0).getAccount_id();
        account.setAccount_id(account_id);

        Set<Account> accounts = new HashSet<>();
        accounts.add(account);
        accounts.add(controllerAccount.getAccountsBySsn(9999999L).get(0));

        assertEquals(1,accounts.size());

        controllerAccount.deleteAccountsBySsn(9999999L);
        controllerCustomer.deleteCustomer(9999999L);
    }

    @Test
    void updateAccount() {
        AccountController controllerAccount = new AccountController(accountService);
        CustomerController controllerCustomer = new CustomerController(customerService);
        // Created customer spesific for this account
        Customer customer = new Customer(9999999L, "asd999@gmail.com", "999999999", "John Doe", "asdasd", 4300);
        controllerCustomer.registerNewCustomer(customer);
        // Created account
        Account account = new Account( 9999999L ,"DOLLAR");
        controllerAccount.addAccount(account);
        // Getting account_id from database and writing to the object
        Long account_id = controllerAccount.getAccountsBySsn(9999999L).get(0).getAccount_id();
        account.setAccount_id(account_id);

        controllerAccount.updateAccount(account_id,4500);
        assertEquals(4500,controllerAccount.getAccountByAccount_id(account_id).getBalance());

        controllerAccount.deleteAccountsBySsn(9999999L);
        controllerCustomer.deleteCustomer(9999999L);
    }
}