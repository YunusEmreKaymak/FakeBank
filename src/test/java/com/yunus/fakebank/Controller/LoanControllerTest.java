package com.yunus.fakebank.Controller;

import com.yunus.fakebank.Entity.Loan;
import com.yunus.fakebank.Entity.Account;
import com.yunus.fakebank.Service.AccountService;
import com.yunus.fakebank.Entity.Customer;
import com.yunus.fakebank.Service.CustomerService;
import com.yunus.fakebank.Service.LoanService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LoanControllerTest {
    @Autowired
    AccountService accountService;
    @Autowired
    LoanService loanService;
    @Autowired
    CustomerService customerService;

    // If we want to test the getLoans method, we need to delete all the rows in db and insert only from this method
    @Test
    void getLoans() {

    }

    @Test
    void getLoansByAccountId() {
        CustomerController controllerCustomer = new CustomerController(customerService);
        Customer customer = new Customer(9999999L, "asd999@gmail.com", "999999999", "John Doe", "asdasd", 4300);
        controllerCustomer.registerNewCustomer(customer);

        AccountController controllerAccount = new AccountController(accountService);
        Account account = new Account(9999999L, "DOLLAR");
        controllerAccount.addAccount(account);

        LoanController loanController = new LoanController(loanService);


        Account accountAdded = controllerAccount.getAccountsBySsn(9999999L).get(0);
        Long account_id = accountAdded.getAccount_id();
        System.out.println(account_id);
        loanController.addLoan(-1, account_id);
        loanController.addLoan(-20, account_id);

        List<Loan> loansByAccountId = loanController.getLoansByAccountId(account_id);

        assertEquals(-1, loansByAccountId.get(0).getAmount());
        assertEquals(-20, loansByAccountId.get(1).getAmount());

        loanController.deleteLoansByAccountId(account_id);
        controllerAccount.deleteAccountsBySsn(9999999L);
        controllerCustomer.deleteCustomer(9999999L);
    }

    // We already tested in getLoansByAccountId method. I will write the same in addLoan.
    @Test
    void addLoan() {
        CustomerController controllerCustomer = new CustomerController(customerService);
        Customer customer = new Customer(9999999L, "asd999@gmail.com", "999999999", "John Doe", "asdasd", 4300);
        controllerCustomer.registerNewCustomer(customer);

        AccountController controllerAccount = new AccountController(accountService);
        Account account = new Account(9999999L, "DOLLAR");
        controllerAccount.addAccount(account);

        LoanController loanController = new LoanController(loanService);


        Account accountAdded = controllerAccount.getAccountsBySsn(9999999L).get(0);
        Long account_id = accountAdded.getAccount_id();
        System.out.println(account_id);
        loanController.addLoan(-1, account_id);
        loanController.addLoan(-20, account_id);

        List<Loan> loansByAccountId = loanController.getLoansByAccountId(account_id);

        assertEquals(-1, loansByAccountId.get(0).getAmount());
        assertEquals(-20, loansByAccountId.get(1).getAmount());

        loanController.deleteLoansByAccountId(account_id);
        controllerAccount.deleteAccountsBySsn(9999999L);
        controllerCustomer.deleteCustomer(9999999L);
    }

    @Test
    void deleteLoan() {
        CustomerController controllerCustomer = new CustomerController(customerService);
        Customer customer = new Customer(9999999L, "asd999@gmail.com", "999999999", "John Doe", "asdasd", 4300);
        controllerCustomer.registerNewCustomer(customer);

        AccountController controllerAccount = new AccountController(accountService);
        Account account = new Account(9999999L, "DOLLAR");
        controllerAccount.addAccount(account);

        LoanController loanController = new LoanController(loanService);


        Account accountAdded = controllerAccount.getAccountsBySsn(9999999L).get(0);
        Long account_id = accountAdded.getAccount_id();
        System.out.println(account_id);
        loanController.addLoan(-1, account_id);
        loanController.addLoan(-20, account_id);

        List<Loan> loans = loanController.getLoans();

        for (int i = 0; i < loans.size(); i++) {
            Loan loan = loans.get(i);

            if (loan.getAccountId().equals(account_id) && loan.getAmount() == -1) {
                loanController.deleteLoan(loan.getLoanId());
                assertThrows(new IllegalStateException().getClass(), () -> loanController.deleteLoan(loan.getLoanId()));
            }
            if (loan.getAccountId().equals(account_id) && loan.getAmount() == -20) {
                loanController.deleteLoan(loan.getLoanId());
                assertThrows(new IllegalStateException().getClass(), () -> loanController.deleteLoan(loan.getLoanId()));
            }

        }
        controllerAccount.deleteAccountsBySsn(9999999L);
        controllerCustomer.deleteCustomer(9999999L);

    }

    @Test
    void updateLoan() {
        CustomerController controllerCustomer = new CustomerController(customerService);
        Customer customer = new Customer(9999999L, "asd999@gmail.com", "999999999", "John Doe", "asdasd", 4300);
        controllerCustomer.registerNewCustomer(customer);

        AccountController controllerAccount = new AccountController(accountService);
        Account account = new Account(9999999L, "DOLLAR");
        controllerAccount.addAccount(account);

        LoanController loanController = new LoanController(loanService);


        Account accountAdded = controllerAccount.getAccountsBySsn(9999999L).get(0);
        Long account_id = accountAdded.getAccount_id();
        System.out.println(account_id);
        loanController.addLoan(-1, account_id);
        loanController.addLoan(-20, account_id);

        List<Loan> loans = loanController.getLoans();

        for (int i = 0; i < loans.size(); i++) {
            Loan loan = loans.get(i);
            if (loan.getAccountId().equals(account_id) && loan.getAmount() == -1) {
                loanController.updateLoan(loan.getLoanId(), -3);
                // We also need to update loan list. If we override to existed loans object, we could break the loop.
                List<Loan> loansInside = loanController.getLoans();
                for (int j = 0; j < loansInside.size(); j++) {
                    Loan loanInside = loansInside.get(j);
                    if (loanInside.getAccountId().equals(account_id) && loanInside.getAmount() == -3) {
                        assertEquals(-3, loanInside.getAmount());
                    }
                }
            }
            if (loan.getAccountId().equals(account_id) && loan.getAmount() == -20) {
                loanController.updateLoan(loan.getLoanId(), -4);
                List<Loan> loansInside = loanController.getLoans();
                for (int j = 0; j < loansInside.size(); j++) {
                    Loan loanInside = loansInside.get(j);
                    if (loanInside.getAccountId().equals(account_id) && loanInside.getAmount() == -4) {
                        assertEquals(-4, loanInside.getAmount());
                    }
                }
            }
        }
        loanController.deleteLoansByAccountId(account_id);
        controllerAccount.deleteAccountsBySsn(9999999L);
        controllerCustomer.deleteCustomer(9999999L);

    }
}