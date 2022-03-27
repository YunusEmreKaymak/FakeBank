package com.yunus.fakebank.Controller;

import com.yunus.fakebank.Entity.Loan;
import com.yunus.fakebank.Service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
@CrossOrigin("*")
public class LoanController {
    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping
    public List<Loan> getLoans() {
        return loanService.getLoans();
    }

    @GetMapping("/{account_id}")
    public List<Loan> getLoansByAccountId(@PathVariable("account_id") Long account_id) {
        return loanService.getLoanByAccountId(account_id);
    }

    @PostMapping("/{amount}/{accountId}")
    public boolean addLoan(@PathVariable("amount") int amount, @PathVariable("accountId") Long account) {
        return loanService.addLoan(amount, account);
    }

    @DeleteMapping("/{id}")
    public void deleteLoan(@PathVariable("id") Long id) {
        loanService.deleteLoan(id);
    }

    @DeleteMapping("/accountId/{accountId}")
    public void deleteLoansByAccountId(@PathVariable("accountId") Long accountId) {
        loanService.deleteLoansByAccountId(accountId);
    }

    @PutMapping("/{id}/{amount}")
    public void updateLoan(@PathVariable("id") Long id, @PathVariable("amount") int amount) {
        loanService.updateLoan(id, amount);
    }
}
