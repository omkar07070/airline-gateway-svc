package com.projects.airline_gateway_svc.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class BookFlightRequest {

    private String userName;
    private String seatType;
    private String seatno;
    private String flightName;
    private String flightId;
    private LocalDate date;
    private String accountNumber;

}
