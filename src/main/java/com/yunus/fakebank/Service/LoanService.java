package com.yunus.fakebank.Service;

import com.yunus.fakebank.Entity.Loan;
import com.yunus.fakebank.Repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class LoanService {
    LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public List<Loan> getLoans() {
        return loanRepository.findAll();
    }

    public List<Loan> getLoanByAccountId(Long account_id) {
        return loanRepository.findLoansByAccountId(account_id);
    }

    public void deleteLoan(Long loanId) {
        if (loanRepository.existsById(loanId)) {
            loanRepository.deleteById(loanId);
        } else {
            throw new IllegalStateException("LoanId not found on deleteLoan");
        }

    }

    public void updateLoan(Long id, int amount) {
        Loan loan = loanRepository.findById(id).orElseThrow(() -> new IllegalStateException("Loan not found"));
        loan.setAmount(amount);
        loanRepository.save(loan);
    }

    public boolean addLoan(int amount, Long accountId) {
        int balance = loanRepository.findBalanceByAccountId(accountId);
        List<Loan> loans = loanRepository.findLoansByAccountId(accountId);
        int allLoan = 0;
        for (int i = 0; i < loans.size(); i++) {
            Loan loan = loans.get(i);
            allLoan += loan.getAmount();
        }

        if ((allLoan + amount) > 5 * balance) {
            return false;
        }
        loanRepository.save(new Loan(accountId, amount));
        loanRepository.setBalanceByAccountId(accountId, balance + amount);
        return true;
    }


    public void deleteLoansByAccountId(Long accountId) {
        loanRepository.deleteLoansByAccountId(accountId);
    }
}
