package com.yunus.fakebank.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "transaction")
@NoArgsConstructor
@Data
@IdClass(TransactionPKId.class)
public class Transaction {
    public Transaction(Long givenId, Long receivenId, String dateTime, int amount, String currency) {
        this.givenId = givenId;
        this.receivenId = receivenId;
        this.dateTime = dateTime;
        this.amount = amount;
        this.currency = currency;
    }

    public Transaction(Long givenId, Long receivenId, String dateTime, int amount) {
        this.givenId = givenId;
        this.receivenId = receivenId;
        this.dateTime = dateTime;
        this.amount = amount;
    }

    @Id
    @Column(name = "given_id")
    private Long givenId;
    @Id
    @Column(name = "receiven_id")
    private Long receivenId;
    @Id
    @Column(name = "date_time")
    private String dateTime;
    @Column(name = "amount")
    private int amount;
    @Column(name = "currency")
    private String currency;


}
