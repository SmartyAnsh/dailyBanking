package com.smartdiscover.service;

import com.smartdiscover.entity.Loan;
import com.smartdiscover.entity.Transaction;
import com.smartdiscover.model.CreateLoanModel;
import com.smartdiscover.repository.LoanRepository;
import com.smartdiscover.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;

@Service
public class LoanService {

    @Autowired
    private ClientService clientService;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public Loan createLoan(CreateLoanModel loanModel) {
        Loan loan = new Loan(loanModel);

        loan.setClient(clientService.getClient(loanModel.getClientId()));

        BigDecimal interest = loanModel.getPrincipal().multiply(loanModel.getInterestRate()).multiply(BigDecimal.valueOf(loanModel.getNoOfYears()));
        loan.setInterest(interest);
        loan.setTotalAmount(loan.getPrincipal().add(loan.getInterest()));

        return loanRepository.save(loan);
    }

    public String disburseLoan(long loanId) {
        Loan loan = loanRepository.findById(loanId).get();

        Transaction transaction = new Transaction(loan, Transaction.Type.DISBURSEMENT, loan.getPrincipal());
        transactionRepository.save(transaction);

        loan.setDisbursed(true);
        loan.setTransactions(new HashSet<>());
        loan.getTransactions().add(transaction);

        loanRepository.save(loan);

        return "OK";
    }

    public String repayLoan(long loanId) {
        Loan loan = loanRepository.findById(loanId).get();

        Transaction transaction = new Transaction(loan, Transaction.Type.REPAYMENT, loan.getTotalAmount());
        transactionRepository.save(transaction);

        loan.setRepaid(true);
        loan.getTransactions().add(transaction);

        loanRepository.save(loan);

        return "OK";
    }

}
