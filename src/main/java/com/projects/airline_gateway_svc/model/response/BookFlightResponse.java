package com.projects.airline_gateway_svc.model.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookFlightResponse {
    private String transactionId;
    private String response;
}
