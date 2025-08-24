package com.projects.airline_gateway_svc.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class BookFlightRequest {

    private int userId;
    private String userName;
    private String mobileNumber;
    private String seatType;
    private String seatno;
    private String flightName;
    private String flightId;
    private LocalDate date;
    private String email;
    private BigDecimal amount;

}
