package com.projects.airline_gateway_svc.model.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FlightDetailsRequest {
    private String flightTakeoff;
    private String flightLanding;
    private LocalDate date;
}
