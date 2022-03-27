package com.yunus.fakebank.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "stock")
public class Stock {

    public Stock(Long accountId, int value, String stockName) {
        this.accountId = accountId;
        this.value = value;
        this.stockName = stockName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long stockId;
    @Column(name = "account_id")
    private Long accountId;
    @Column(name = "value")
    private int value;
    @Column(name = "stock_name")
    private String stockName;

}
