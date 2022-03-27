package com.yunus.fakebank.Controller;

import com.yunus.fakebank.Entity.Card;
import com.yunus.fakebank.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
@CrossOrigin("*")
public class CardController {
    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public List<Card> getCards() {
        return cardService.getCards();
    }

    @GetMapping("/{id}")
    public List<Card> getCardsByAccount(@PathVariable("id") Long id) {
        return cardService.findCardsByAccount(id);
    }

    @PostMapping
    public void addCard(@RequestBody Card card) {
        cardService.addCard(card);
    }

    @DeleteMapping("/{id}")
    public void deleteCard(@PathVariable("id") Long id) {
        cardService.deleteCard(id);
    }

    @DeleteMapping("/accountId/{accountId}")
    public void deleteCardsByAccountId(@PathVariable("accountId") Long accountId) {
        cardService.deleteCardsByAccountId(accountId);
    }

    @PutMapping("/{id}/{password}")
    public void updateCard(@PathVariable("id") Long id, @PathVariable("password") String password) {
        cardService.updateCard(id, password);
    }
}
