package com.yunus.fakebank.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Loan")
public class Loan {
    public Loan(Long accountId, int amount) {
        this.accountId = accountId;
        this.amount = amount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "loan_id")
    private Long loanId;
    @Column(name = "account_id")
    private Long accountId;
    @Column(name = "amount")
    private int amount;


}
