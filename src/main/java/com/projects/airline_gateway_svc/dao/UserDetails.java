package com.projects.airline_gateway_svc.dao;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Table(name = "user_details")
@Entity
public class UserDetails
{
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "account_balance")
    private String accountBalance;

    @Column(name = "dob")
    private LocalDate dob;

}
