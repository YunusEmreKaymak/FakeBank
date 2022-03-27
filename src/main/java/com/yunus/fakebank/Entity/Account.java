package com.yunus.fakebank.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account {
    public Account(Long ssn, String accountType) {
        this.ssn = ssn;
        this.accountType = accountType;
        this.balance = 0;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long account_id;
    @Column(name = "ssn", updatable = false)
    private Long ssn;
    private String accountType;
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long iban;
    private int balance;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    @Transient
    List<Loan> loans = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    @Transient
    List<Card> cards = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    @Transient
    List<Stock> stocks = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "given_id", referencedColumnName = "account_id")
    @Transient
    List<Transaction> transactions = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "receiven_id", referencedColumnName = "account_id")
    @Transient
    List<Transaction> transactions2 = new ArrayList<>();
}
