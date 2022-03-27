package com.yunus.fakebank.Controller;

import com.yunus.fakebank.Entity.Stock;
import com.yunus.fakebank.Service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
@CrossOrigin("*")
public class StockController {

    StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    public List<Stock> getStocks() {
        return stockService.getStocks();
    }

    @GetMapping("/{id}")
    public List<Stock> getStocksByAccount(@PathVariable("id") Long id) {
        return stockService.getStocksByAccount(id);
    }

    @PostMapping
    public void addStock(@RequestBody Stock stock) {
        stockService.addStock(stock);
    }

    @DeleteMapping("/{id}")
    public void deleteStock(@PathVariable("id") Long id) {
        stockService.deleteStock(id);
    }

    @DeleteMapping("/accountId/{accountId}")
    public void deleteStocksByAccountId(@PathVariable("accountId") Long accountId) {
        stockService.deleteStocksByAccountId(accountId);
    }

    @PutMapping("/{id}/{value}")
    public void updateStock(@PathVariable("id") Long id, @PathVariable("value") int value) {
        stockService.updateStock(id, value);
    }

}
