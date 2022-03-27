package com.yunus.fakebank.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "card")
public class Card {
    public Card(Long accountId, String password, String cardType) {
        this.accountId = accountId;
        this.password = password;
        this.cardType = cardType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cardNumber;
    @Column(name = "account_id")
    private Long accountId;
    @Column(name = "password")
    private String password;
    @Column(name = "card_type")
    private String cardType;

}
