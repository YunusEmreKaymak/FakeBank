package com.yunus.fakebank.Service;

import com.yunus.fakebank.Entity.Stock;
import com.yunus.fakebank.Repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    StockRepository stockRepository;

    @Autowired
    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<Stock> getStocks() {
        return stockRepository.findAll();
    }

    public List<Stock> getStocksByAccount(Long id) {
        return stockRepository.findStocksByAccountId(id);
    }

    public void addStock(Stock stock) {
        int balance = stockRepository.getBalance(stock.getAccountId());

        stockRepository.decreaseAmountFromBalance(balance - stock.getValue(), stock.getAccountId());
        System.out.println(balance + "---------------------------------------------------------------------");
        stockRepository.save(stock);
    }

    public void deleteStock(Long id) {
        if (stockRepository.existsById(id)) {
            stockRepository.deleteById(id);
        } else {
            throw new IllegalStateException("Id not found on deleteStock");
        }
    }

    public void updateStock(Long id, int value) {
        Stock stockUpdate = stockRepository.findById(id).orElseThrow(() -> new IllegalStateException("Id not found on updateStock"));
        stockUpdate.setValue(value);
        stockRepository.save(stockUpdate);
    }

    public void deleteStocksByAccountId(Long accountId) {
        stockRepository.deleteStocksByAccountId(accountId);
    }
}
