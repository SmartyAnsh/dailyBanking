package com.smartdiscover.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateLoanModel {

    private Long clientId;

    private BigDecimal principal;

    private BigDecimal interestRate;

    private int noOfYears;
}
