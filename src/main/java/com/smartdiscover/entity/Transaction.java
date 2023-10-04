package com.smartdiscover.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "LOAN_ID", nullable = false)
    private Loan loan;

    private Type type;

    private BigDecimal amount;

    public Transaction() {

    }

    public Transaction(Loan loan, Type type, BigDecimal amount) {
        this.loan = loan;
        this.type = type;
        this.amount = amount;
    }

    public enum Type {

        DISBURSEMENT,

        REPAYMENT

    }

}


