package com.smartdiscover.dailybanking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smartdiscover.dailybanking.model.CreateLoanModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "CLIENT_ID", nullable = false)
    private Client client;

    private BigDecimal principal;
    private BigDecimal interestRate;
    private int noOfYears;

    private BigDecimal totalAmount;
    private BigDecimal interest;

    private Boolean disbursed;
    private Boolean repaid;

    private Date disbursedDate;
    private Date repaidDate;

    public Loan() {

    }

    public Loan(CreateLoanModel loanModel) {
        this.principal = loanModel.getPrincipal();
        this.interestRate = loanModel.getInterestRate();
        this.noOfYears = loanModel.getNoOfYears();
    }
}
