package com.projects.airline_gateway_svc.dao;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transaction_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDetails {

    @Id
    @Column(name = "t_id")
    private String tId;

    @Column(name = "username")
    private String username;

    @Column(name = "account_no")
    private String accountNo;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "acc_balance")
    private BigDecimal accBalance;

    @Column(name = "status")
    private String status;

    @Column(name = "transaction_date")
    private LocalDate transactionDate;
}