package com.yunus.fakebank.Controller;


import com.yunus.fakebank.Entity.Transaction;
import com.yunus.fakebank.Entity.TransactionPKId;
import com.yunus.fakebank.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@CrossOrigin("*")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> getTransactions() {
        return transactionService.getTransactions();
    }


    @GetMapping("/g/{idGiven}")
    public List<Transaction> getTransactionsByGiven(@PathVariable("idGiven") Long idGiven) {
        return transactionService.getTransactionsByGiven(idGiven);
    }

    @PostMapping
    public void addTransaction(@RequestBody Transaction transaction) {
        transactionService.addTransaction(transaction);
    }

    @DeleteMapping
    public void deleteTransaction(@RequestBody TransactionPKId transactionPKId) {
        transactionService.deleteTransaction(transactionPKId);
    }

    @DeleteMapping("/r/{receivenId}")
    public void deleteTransactionsByReceivenId(@PathVariable("receivenId") Long receivenId) {
        transactionService.deleteTransactionsByReceivenId(receivenId);
    }

    @DeleteMapping("/g/{givenId}")
    public void deleteTransactionsByGiven(@PathVariable("givenId") Long givenId) {
        transactionService.deleteTransactionsByGivenId(givenId);
    }

    // The transactions cannot be changeable

//    @PutMapping
//    public void updateTransaction(@RequestBody Transaction transaction){
//        transactionService.updateTransaction(transaction);
//    }

}
