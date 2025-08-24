package com.projects.airline_gateway_svc.model.response;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class PaymentMessage {

    private int userId;
    private String username;
    private String mobileNumber;
    private String email;
    private String seatNumber;
    private String flightName;
    private String transactionId;;
    private BigDecimal amount;
}
