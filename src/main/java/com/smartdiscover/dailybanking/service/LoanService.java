package com.smartdiscover.service;

import com.smartdiscover.entity.Loan;
import com.smartdiscover.model.CreateLoanModel;
import com.smartdiscover.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class LoanService {

    @Autowired
    private ClientService clientService;

    @Autowired
    private LoanRepository loanRepository;

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

        loan.setDisbursed(true);
        loan.setDisbursedDate(new Date());

        loanRepository.save(loan);

        return "OK";
    }

    public String repayLoan(long loanId) {
        Loan loan = loanRepository.findById(loanId).get();

        loan.setRepaid(true);
        loan.setRepaidDate(new Date());

        loanRepository.save(loan);

        return "OK";
    }

}
