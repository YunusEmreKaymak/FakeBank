package com.yunus.fakebank.Service;

import com.yunus.fakebank.Entity.Transaction;
import com.yunus.fakebank.Entity.TransactionPKId;
import com.yunus.fakebank.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Hashtable;
import java.util.List;

@Service
public class TransactionService {
    TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    Hashtable<String, String> currencyValues = new Hashtable<>();

    public Hashtable<String, String> refreshCurrencies() {
        currencyValues.put("DOLLAR", "1");
        currencyValues.put("BITCOIN", "44590");
        currencyValues.put("GOLD", "1958");
        return currencyValues;
    }


    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getTransactionsByGiven(Long idGiven) {
        List<Transaction> transactionsByGivenId = transactionRepository.getTransactionsByGivenId(idGiven);
        List<Transaction> transactionsByReceivenId = transactionRepository.getTransactionsByReceivenId(idGiven);
        for (Transaction transaction : transactionsByReceivenId) {
            transactionsByGivenId.add(transaction);
        }
        return transactionsByGivenId;
    }

    public List<Transaction> getTransactionsByReceiven(Long idReceiven) {
        return transactionRepository.getTransactionsByReceivenId(idReceiven);
    }

    public void addTransaction(Transaction transaction) {

        int amount = transaction.getAmount();

        if (transactionRepository.existAccountTypeFromAccountId(transaction.getReceivenId()).equals("true")) {

            transactionRepository.decreaseAmountFromBalance(amount, transaction.getGivenId());

            // When you increase receiver, you need to convert to the currency of receiver

            String accountTypeOfReceiverId = transactionRepository.getAccountTypeFromAccountId(transaction.getReceivenId());

            String currencyNameGiver = transactionRepository.getAccountTypeFromAccountId(transaction.getGivenId());

            Hashtable<String, String> currencyValuesRefreshed = refreshCurrencies();

            int currencyValueGiver = Integer.parseInt(currencyValuesRefreshed.get(currencyNameGiver));

            int currencyValueReceiver = Integer.parseInt(currencyValuesRefreshed.get(accountTypeOfReceiverId));


            int realAmount = (currencyValueGiver * amount) / currencyValueReceiver;


            transactionRepository.increaseAmountFromBalance(realAmount, transaction.getReceivenId());

            transactionRepository.save(transaction);
        } else {
            throw new NullPointerException("account not found on transaction");
        }

    }

    public void deleteTransaction(TransactionPKId transactionPKId) {
        if (transactionRepository.existsById(transactionPKId)) {
            transactionRepository.deleteById(transactionPKId);
        } else {
            throw new IllegalStateException("Transaction not found on delete");
        }
    }
    // Transaction is not updatable
//    public void updateTransaction(Transaction transaction) {
//        TransactionPKId transactionPKId = new TransactionPKId(transaction.getGivenId(), transaction.getReceivenId(), transaction.getDateTime());
//        Transaction transactionUpdate = transactionRepository.findById(transactionPKId).orElseThrow(() -> new IllegalStateException("Transaction not found on update"));
//        transactionUpdate.setAmount(transaction.getAmount());
//        transactionRepository.save(transactionUpdate);
//    }

    public void deleteTransactionsByGivenId(Long givenId) throws IllegalStateException {
        transactionRepository.deleteTransactionsByGivenId(givenId);
    }

    public void deleteTransactionsByReceivenId(Long receivenId) throws IllegalStateException {
        transactionRepository.deleteTransactionsByReceivenId(receivenId);
    }
}
