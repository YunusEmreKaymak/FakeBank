package com.yunus.fakebank.Controller;

import com.yunus.fakebank.Controller.StockController;
import com.yunus.fakebank.Entity.Account;
import com.yunus.fakebank.Entity.Stock;
import com.yunus.fakebank.Controller.AccountController;
import com.yunus.fakebank.Service.AccountService;
import com.yunus.fakebank.Entity.Customer;
import com.yunus.fakebank.Controller.CustomerController;
import com.yunus.fakebank.Service.CustomerService;
import com.yunus.fakebank.Service.StockService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StockControllerTest {
    @Autowired
    AccountService accountService;
    @Autowired
    StockService stockService;
    @Autowired
    CustomerService customerService;

    @Test
    void getStocks() {
    }

    @Test
    void getStocksByAccount() {
        CustomerController controllerCustomer = new CustomerController(customerService);
        Customer customer = new Customer(9999999L, "asd999@gmail.com", "999999999", "John Doe", "asdasd", 4300);
        controllerCustomer.registerNewCustomer(customer);

        AccountController controllerAccount = new AccountController(accountService);
        Account account = new Account(9999999L, "DOLLAR");
        controllerAccount.addAccount(account);

        StockController stockController = new StockController(stockService);


        Account accountAdded = controllerAccount.getAccountsBySsn(9999999L).get(0);
        Long account_id = accountAdded.getAccount_id();

        stockController.addStock(new Stock(account_id, 200, "FB"));
        stockController.addStock(new Stock(account_id, 300, "TSLA"));

        List<Stock> stocksByAccount = stockController.getStocksByAccount(account_id);

        assertEquals("FB200", stocksByAccount.get(0).getStockName() + stocksByAccount.get(0).getValue());
        assertEquals("TSLA300", stocksByAccount.get(1).getStockName() + stocksByAccount.get(1).getValue());

        stockController.deleteStocksByAccountId(account_id);
        controllerAccount.deleteAccountsBySsn(9999999L);
        controllerCustomer.deleteCustomer(9999999L);
    }

    @Test
    void addStock() {
        CustomerController controllerCustomer = new CustomerController(customerService);
        Customer customer = new Customer(9999999L, "asd999@gmail.com", "999999999", "John Doe", "asdasd", 4300);
        controllerCustomer.registerNewCustomer(customer);

        AccountController controllerAccount = new AccountController(accountService);
        Account account = new Account(9999999L, "DOLLAR");
        controllerAccount.addAccount(account);

        StockController stockController = new StockController(stockService);


        Account accountAdded = controllerAccount.getAccountsBySsn(9999999L).get(0);
        Long account_id = accountAdded.getAccount_id();

        stockController.addStock(new Stock(account_id, 200, "FB"));
        stockController.addStock(new Stock(account_id, 300, "TSLA"));

        List<Stock> stocksByAccount = stockController.getStocksByAccount(account_id);

        assertEquals("FB200", stocksByAccount.get(0).getStockName() + stocksByAccount.get(0).getValue());
        assertEquals("TSLA300", stocksByAccount.get(1).getStockName() + stocksByAccount.get(1).getValue());

        stockController.deleteStocksByAccountId(account_id);
        controllerAccount.deleteAccountsBySsn(9999999L);
        controllerCustomer.deleteCustomer(9999999L);
    }

    @Test
    void deleteStock() {
        CustomerController controllerCustomer = new CustomerController(customerService);
        Customer customer = new Customer(9999999L, "asd999@gmail.com", "999999999", "John Doe", "asdasd", 4300);
        controllerCustomer.registerNewCustomer(customer);

        AccountController controllerAccount = new AccountController(accountService);
        Account account = new Account(9999999L, "DOLLAR");
        controllerAccount.addAccount(account);

        StockController stockController = new StockController(stockService);


        Account accountAdded = controllerAccount.getAccountsBySsn(9999999L).get(0);
        Long account_id = accountAdded.getAccount_id();

        stockController.addStock(new Stock(account_id, 200, "FB"));
        stockController.addStock(new Stock(account_id, 300, "TSLA"));

        List<Stock> stocks = stockController.getStocks();

        for (int i = 0; i < stocks.size(); i++) {
            Stock stock = stocks.get(i);

            if (stock.getAccountId().equals(account_id) && stock.getValue() == 200 && stock.getStockName().equals("FB")) {
                stockController.deleteStock(stock.getStockId());
                assertThrows(new IllegalStateException().getClass(), () -> stockController.deleteStock(stock.getStockId()));
            }
            if (stock.getAccountId().equals(account_id) && stock.getValue() == 300 && stock.getStockName().equals("TSLA")) {
                stockController.deleteStock(stock.getStockId());
                assertThrows(new IllegalStateException().getClass(), () -> stockController.deleteStock(stock.getStockId()));
            }

        }
        controllerAccount.deleteAccountsBySsn(9999999L);
        controllerCustomer.deleteCustomer(9999999L);

    }

    @Test
    void updateStock() {
        CustomerController controllerCustomer = new CustomerController(customerService);
        Customer customer = new Customer(9999999L, "asd999@gmail.com", "999999999", "John Doe", "asdasd", 4300);
        controllerCustomer.registerNewCustomer(customer);

        AccountController controllerAccount = new AccountController(accountService);
        Account account = new Account(9999999L, "DOLLAR");
        controllerAccount.addAccount(account);

        StockController stockController = new StockController(stockService);


        Account accountAdded = controllerAccount.getAccountsBySsn(9999999L).get(0);
        Long account_id = accountAdded.getAccount_id();

        stockController.addStock(new Stock(account_id, 200, "FB"));
        stockController.addStock(new Stock(account_id, 300, "TSLA"));

        List<Stock> stocks = stockController.getStocks();

        for (int i = 0; i < stocks.size(); i++) {
            Stock stock = stocks.get(i);

            if (stock.getAccountId().equals(account_id) && stock.getValue() == 200 && stock.getStockName().equals("FB")) {
                stockController.updateStock(stock.getStockId(), 400);
                // We also need to update loan list. If we override to existed loans object, we could break the loop.
                List<Stock> stocksInside = stockController.getStocks();
                for (int j = 0; j < stocksInside.size(); j++) {
                    Stock stockInside = stocksInside.get(j);

                    if (stockInside.getAccountId().equals(account_id) && stockInside.getValue() == 400 && stockInside.getStockName().equals("FB")) {
                        assertEquals("FB400", stockInside.getStockName() + stockInside.getValue());
                    }
                }
            }
            if (stock.getAccountId().equals(account_id) && stock.getValue() == 300 && stock.getStockName().equals("TSLA")) {
                stockController.updateStock(stock.getStockId(), 600);
                List<Stock> stocksInside = stockController.getStocks();
                for (int j = 0; j < stocksInside.size(); j++) {
                    Stock stockInside = stocksInside.get(j);
                    if (stockInside.getAccountId().equals(account_id) && stockInside.getValue() == 600 && stockInside.getStockName().equals("TSLA")) {
                        assertEquals("TSLA600", stockInside.getStockName() + stockInside.getValue());
                    }
                }
            }
        }
        stockController.deleteStocksByAccountId(account_id);
        controllerAccount.deleteAccountsBySsn(9999999L);
        controllerCustomer.deleteCustomer(9999999L);
    }
}