package com.yunus.fakebank.Controller;

import com.yunus.fakebank.Entity.Card;
import com.yunus.fakebank.Entity.Account;
import com.yunus.fakebank.Service.AccountService;
import com.yunus.fakebank.Entity.Customer;
import com.yunus.fakebank.Service.CustomerService;
import com.yunus.fakebank.Service.CardService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CardControllerTest {
    @Autowired
    AccountService accountService;
    @Autowired
    CardService cardService;
    @Autowired
    CustomerService customerService;

    @Test
    void getCards() {

    }

    @Test
    void getCardsByAccount() {
        CustomerController controllerCustomer = new CustomerController(customerService);
        Customer customer = new Customer(9999999L, "asd999@gmail.com", "999999999", "John Doe", "asdasd", 4300);
        controllerCustomer.registerNewCustomer(customer);

        AccountController controllerAccount = new AccountController(accountService);
        Account account = new Account(9999999L, "DOLLAR");
        controllerAccount.addAccount(account);

        CardController cardController = new CardController(cardService);


        Account accountAdded = controllerAccount.getAccountsBySsn(9999999L).get(0);
        Long account_id = accountAdded.getAccount_id();
        System.out.println(account_id);
        cardController.addCard(new Card(account_id, "asd", "DEBIT"));
        cardController.addCard(new Card(account_id, "zxc", "VIRTUAL"));

        List<Card> cardsByAccount = cardController.getCardsByAccount(account_id);

        assertEquals("asd", cardsByAccount.get(0).getPassword());
        assertEquals("zxc", cardsByAccount.get(1).getPassword());

        cardController.deleteCardsByAccountId(account_id);
        controllerAccount.deleteAccountsBySsn(9999999L);
        controllerCustomer.deleteCustomer(9999999L);
    }

    @Test
    void addCard() {
        CustomerController controllerCustomer = new CustomerController(customerService);
        Customer customer = new Customer(9999999L, "asd999@gmail.com", "999999999", "John Doe", "asdasd", 4300);
        controllerCustomer.registerNewCustomer(customer);

        AccountController controllerAccount = new AccountController(accountService);
        Account account = new Account(9999999L, "DOLLAR");
        controllerAccount.addAccount(account);

        CardController cardController = new CardController(cardService);


        Account accountAdded = controllerAccount.getAccountsBySsn(9999999L).get(0);
        Long account_id = accountAdded.getAccount_id();
        System.out.println(account_id);
        cardController.addCard(new Card(account_id, "asd", "DEBIT"));
        cardController.addCard(new Card(account_id, "zxc", "VIRTUAL"));

        List<Card> cards = cardController.getCardsByAccount(account_id);

        assertEquals("asd", cards.get(0).getPassword());
        assertEquals("zxc", cards.get(1).getPassword());

        cardController.deleteCardsByAccountId(account_id);
        controllerAccount.deleteAccountsBySsn(9999999L);
        controllerCustomer.deleteCustomer(9999999L);
    }

    @Test
    void deleteCard() {
        CustomerController controllerCustomer = new CustomerController(customerService);
        Customer customer = new Customer(9999999L, "asd999@gmail.com", "999999999", "John Doe", "asdasd", 4300);
        controllerCustomer.registerNewCustomer(customer);

        AccountController controllerAccount = new AccountController(accountService);
        Account account = new Account(9999999L, "DOLLAR");
        controllerAccount.addAccount(account);

        CardController cardController = new CardController(cardService);


        Account accountAdded = controllerAccount.getAccountsBySsn(9999999L).get(0);
        Long account_id = accountAdded.getAccount_id();
        System.out.println(account_id);
        cardController.addCard(new Card(account_id, "asd", "DEBIT"));
        cardController.addCard(new Card(account_id, "zxc", "VIRTUAL"));

        List<Card> cards = cardController.getCardsByAccount(account_id);

        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);

            if (card.getAccountId().equals(account_id) && card.getPassword().equals("asd")) {
                cardController.deleteCard(card.getCardNumber());
                assertThrows(new IllegalStateException().getClass(), () -> cardController.deleteCard(card.getCardNumber()));
            }
            if (card.getAccountId().equals(account_id) && card.getPassword().equals("zxc")) {
                cardController.deleteCard(card.getCardNumber());
                assertThrows(new IllegalStateException().getClass(), () -> cardController.deleteCard(card.getCardNumber()));
            }

        }
        controllerAccount.deleteAccountsBySsn(9999999L);
        controllerCustomer.deleteCustomer(9999999L);
    }

    @Test
    void updateCard() {
        CustomerController controllerCustomer = new CustomerController(customerService);
        Customer customer = new Customer(9999999L, "asd999@gmail.com", "999999999", "John Doe", "asdasd", 4300);
        controllerCustomer.registerNewCustomer(customer);

        AccountController controllerAccount = new AccountController(accountService);
        Account account = new Account(9999999L, "DOLLAR");
        controllerAccount.addAccount(account);

        CardController cardController = new CardController(cardService);


        Account accountAdded = controllerAccount.getAccountsBySsn(9999999L).get(0);
        Long account_id = accountAdded.getAccount_id();
        System.out.println(account_id);
        cardController.addCard(new Card(account_id, "asd", "DEBIT"));
        cardController.addCard(new Card(account_id, "zxc", "VIRTUAL"));

        List<Card> cards = cardController.getCardsByAccount(account_id);

        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            if (card.getAccountId().equals(account_id) && card.getPassword().equals("asd")) {
                cardController.updateCard(card.getCardNumber(), "sdf");
                // We also need to update card list. If we override to existed cards object, we could break the loop.
                List<Card> cardsInside = cardController.getCards();
                for (int j = 0; j < cardsInside.size(); j++) {
                    Card cardInside = cardsInside.get(j);
                    if (cardInside.getAccountId().equals(account_id) && cardInside.getPassword().equals("sdf")) {
                        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAA--"+cardInside.getPassword());
                        assertEquals("sdf", cardInside.getPassword());
                    }
                }
            }
            if (card.getAccountId().equals(account_id) && card.getPassword().equals("zxc")) {
                cardController.updateCard(card.getCardNumber(), "xcv");
                List<Card> cardsInsıde = cardController.getCards();
                for (int j = 0; j < cardsInsıde.size(); j++) {
                    Card cardInside = cardsInsıde.get(j);
                    if (cardInside.getAccountId().equals(account_id) && cardInside.getPassword().equals("xcv")) {
                        System.out.println("BBBBBBBBBBBBBBBBBBBBBBB--"+cardInside.getPassword());
                        assertEquals("xcv", cardInside.getPassword());
                    }
                }
            }
        }
        cardController.deleteCardsByAccountId(account_id);
        controllerAccount.deleteAccountsBySsn(9999999L);
        controllerCustomer.deleteCustomer(9999999L);

    }
}