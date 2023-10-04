package com.smartdiscover.controller;

import com.smartdiscover.entity.Loan;
import com.smartdiscover.model.CreateLoanModel;
import com.smartdiscover.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    public ResponseEntity<Loan> getLoan(@PathVariable("id") long id) {
        Loan loan = loanService.getLoan(id);
        if (loan != null) {
            return new ResponseEntity<>(loan, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Loan> createLoan(@RequestBody CreateLoanModel loanModel) {
        return new ResponseEntity<>(loanService.createLoan(loanModel), HttpStatus.OK);
    }

    @PostMapping(value = "/disburse/{id}")
    public ResponseEntity<String> disburseLoan(@PathVariable("id") long loanId) {
        return new ResponseEntity<>(loanService.disburseLoan(loanId), HttpStatus.OK);
    }

    @PostMapping(value = "/repay/{id}")
    public ResponseEntity<String> repayLoan(@PathVariable("id") long loanId) {
        return new ResponseEntity<>(loanService.repayLoan(loanId), HttpStatus.OK);
    }

}
