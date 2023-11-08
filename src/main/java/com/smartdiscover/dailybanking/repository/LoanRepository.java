package com.smartdiscover.dailybanking.repository;

import com.smartdiscover.dailybanking.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
}
