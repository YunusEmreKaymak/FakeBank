package com.yunus.fakebank.Service;

import com.yunus.fakebank.Entity.Card;
import com.yunus.fakebank.Repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<Card> getCards() {
        return cardRepository.findAll();
    }

    public List<Card> findCardsByAccount(Long id) {
        return cardRepository.findCardsByAccountId(id);
    }

    public void deleteCard(Long cardNumber) {
        if (cardRepository.existsById(cardNumber)) {
            cardRepository.deleteById(cardNumber);
        } else {
            throw new IllegalStateException("IBAN not found on deleteCard");
        }
    }

    public void addCard(Card card) {
        if (cardRepository.existsById(card.getAccountId())) {
            throw new IllegalStateException("ERROR: Card already exist");
        } else {
            cardRepository.save(card);
        }
    }

    public void updateCard(Long id, String password) {
        Card cardUpdate = cardRepository.findById(id).orElseThrow(() -> new IllegalStateException("Card Not found on update"));
        cardUpdate.setPassword(password);
        cardRepository.save(cardUpdate);
    }

    public void deleteCardsByAccountId(Long accountId) {
        cardRepository.deleteCardsByAccountId(accountId);
    }
}
