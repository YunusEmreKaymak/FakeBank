package com.yunus.fakebank.Controller;

import com.yunus.fakebank.Controller.TransactionController;
import com.yunus.fakebank.Entity.Account;
import com.yunus.fakebank.Entity.Transaction;
import com.yunus.fakebank.Controller.AccountController;
import com.yunus.fakebank.Entity.TransactionPKId;
import com.yunus.fakebank.Service.AccountService;
import com.yunus.fakebank.Entity.Customer;
import com.yunus.fakebank.Controller.CustomerController;
import com.yunus.fakebank.Service.CustomerService;
import com.yunus.fakebank.Service.TransactionService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TransactionControllerTest {
    @Autowired
    AccountService accountService;
    @Autowired
    TransactionService transactionService;
    @Autowired
    CustomerService customerService;

    @Test
    void getTransactions() {

    }

    @Test
    void getTransactionsByGiven() {
        CustomerController controllerCustomer = new CustomerController(customerService);
        Customer customer = new Customer(9999999L, "asd999@gmail.com", "999999999", "John Doe", "asdasd", 4300);
        controllerCustomer.registerNewCustomer(customer);

        AccountController controllerAccount = new AccountController(accountService);
        Account account = new Account(9999999L, "DOLLAR");
        controllerAccount.addAccount(account);
        Account account2 = new Account(9999999L, "DOLLAR");
        controllerAccount.addAccount(account2);

        TransactionController transactionController = new TransactionController(transactionService);


        Account accountAdded = controllerAccount.getAccountsBySsn(9999999L).get(0);
        Long account_id = accountAdded.getAccount_id();
        accountAdded = controllerAccount.getAccountsBySsn(9999999L).get(1);
        Long account_id2 = accountAdded.getAccount_id();

        transactionController.addTransaction(new Transaction(account_id, account_id2, "21:00 17/10/2022", 200, "DOLLAR"));
        transactionController.addTransaction(new Transaction(account_id, account_id2, "21:01 17/10/2022", 300, "DOLLAR"));
        transactionController.addTransaction(new Transaction(account_id2, account_id, "22:00 17/10/2022", 400, "DOLLAR"));

        List<Transaction> transactionsByGiven = transactionController.getTransactionsByGiven(account_id);

        assertEquals(200, transactionsByGiven.get(0).getAmount());
        assertEquals(300, transactionsByGiven.get(1).getAmount());
        assertEquals(400, transactionsByGiven.get(2).getAmount());

        transactionController.deleteTransactionsByReceivenId(account_id);
        transactionController.deleteTransactionsByReceivenId(account_id2);
        controllerAccount.deleteAccountsBySsn(9999999L);
        controllerCustomer.deleteCustomer(9999999L);

    }

    @Test
    void addTransaction() {
        CustomerController controllerCustomer = new CustomerController(customerService);
        Customer customer = new Customer(9999999L, "asd999@gmail.com", "999999999", "John Doe", "asdasd", 4300);
        controllerCustomer.registerNewCustomer(customer);

        AccountController controllerAccount = new AccountController(accountService);
        Account account = new Account(9999999L, "DOLLAR");
        controllerAccount.addAccount(account);
        Account account2 = new Account(9999999L, "DOLLAR");
        controllerAccount.addAccount(account2);

        TransactionController transactionController = new TransactionController(transactionService);


        Account accountAdded = controllerAccount.getAccountsBySsn(9999999L).get(0);
        Long account_id = accountAdded.getAccount_id();
        accountAdded = controllerAccount.getAccountsBySsn(9999999L).get(1);
        Long account_id2 = accountAdded.getAccount_id();

        transactionController.addTransaction(new Transaction(account_id, account_id2, "21:00 17/10/2022", 200, "DOLLAR"));
        transactionController.addTransaction(new Transaction(account_id, account_id2, "21:01 17/10/2022", 300, "DOLLAR"));
        transactionController.addTransaction(new Transaction(account_id2, account_id, "22:00 17/10/2022", 400, "DOLLAR"));

        List<Transaction> transactionsByGiven = transactionController.getTransactionsByGiven(account_id);

        assertEquals(200, transactionsByGiven.get(0).getAmount());
        assertEquals(300, transactionsByGiven.get(1).getAmount());
        assertEquals(400, transactionsByGiven.get(2).getAmount());

        transactionController.deleteTransactionsByReceivenId(account_id);
        transactionController.deleteTransactionsByReceivenId(account_id2);
        controllerAccount.deleteAccountsBySsn(9999999L);
        controllerCustomer.deleteCustomer(9999999L);
    }

    @Test
    void deleteTransaction() {
        CustomerController controllerCustomer = new CustomerController(customerService);
        Customer customer = new Customer(9999999L, "asd999@gmail.com", "999999999", "John Doe", "asdasd", 4300);
        controllerCustomer.registerNewCustomer(customer);

        AccountController controllerAccount = new AccountController(accountService);
        Account account = new Account(9999999L, "DOLLAR");
        controllerAccount.addAccount(account);
        Account account2 = new Account(9999999L, "DOLLAR");
        controllerAccount.addAccount(account2);

        TransactionController transactionController = new TransactionController(transactionService);


        Account accountAdded = controllerAccount.getAccountsBySsn(9999999L).get(0);
        Long account_id = accountAdded.getAccount_id();
        accountAdded = controllerAccount.getAccountsBySsn(9999999L).get(1);
        Long account_id2 = accountAdded.getAccount_id();

        transactionController.addTransaction(new Transaction(account_id, account_id2, "21:00 17/10/2022", 200 ,"DOLLAR"));
        transactionController.addTransaction(new Transaction(account_id, account_id2, "21:01 17/10/2022", 300, "DOLLAR"));
        transactionController.addTransaction(new Transaction(account_id2, account_id, "22:00 17/10/2022", 400, "DOLLAR"));

        List<Transaction> transactionsByGiven = transactionController.getTransactionsByGiven(account_id);

        for (int i = 0; i < transactionsByGiven.size(); i++) {
            Transaction transaction = transactionsByGiven.get(i);

            if (transaction.getGivenId().equals(account_id) && transaction.getDateTime().equals("21:01 17/10/2022")) {
                System.out.println("???----------------" + transaction.getGivenId() + "--------------" + transaction.getReceivenId() + "-----------" + transaction.getDateTime());
                transactionController.deleteTransaction(new TransactionPKId(transaction.getGivenId(),
                        transaction.getReceivenId(),
                        transaction.getDateTime()));
                assertThrows(new IllegalStateException().getClass(), () -> transactionController.deleteTransaction(
                        new TransactionPKId(transaction.getGivenId(),
                                transaction.getReceivenId(),
                                transaction.getDateTime())));
            }
            if (transaction.getGivenId().equals(account_id) && transaction.getDateTime().equals("21:00 17/10/2022")) {
                System.out.println("???----------------" + transaction.getGivenId() + "--------------" + transaction.getReceivenId() + "-----------" + transaction.getDateTime());

                transactionController.deleteTransaction(new TransactionPKId(transaction.getGivenId(),
                        transaction.getReceivenId(),
                        transaction.getDateTime()));
                assertThrows(new IllegalStateException().getClass(), () -> transactionController.deleteTransaction(
                        new TransactionPKId(transaction.getGivenId(),
                                transaction.getReceivenId(),
                                transaction.getDateTime())));
            }
            if (transaction.getGivenId().equals(account_id2) && transaction.getDateTime().equals("22:00 17/10/2022")) {
                System.out.println("???----------------" + transaction.getGivenId() + "--------------" + transaction.getReceivenId() + "-----------" + transaction.getDateTime());
                transactionController.deleteTransaction(new TransactionPKId(transaction.getGivenId(),
                        transaction.getReceivenId(),
                        transaction.getDateTime()));
                assertThrows(new IllegalStateException().getClass(), () -> transactionController.deleteTransaction(
                        new TransactionPKId(transaction.getGivenId(),
                                transaction.getReceivenId(),
                                transaction.getDateTime())));
            }
        }
        controllerAccount.deleteAccountsBySsn(9999999L);
        controllerCustomer.deleteCustomer(9999999L);
    }
}