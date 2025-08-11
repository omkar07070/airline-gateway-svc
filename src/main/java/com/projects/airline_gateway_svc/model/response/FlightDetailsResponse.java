package com.projects.airline_gateway_svc.model.response;

import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class FlightDetailsResponse {
    private String flightId;
    private String flightName;
    private LocalDate flightDate;
    private LocalTime flightTakeoffTime;
    private LocalTime flightLandingTime;
    private String flightDeparture;
    private String flightDestination;

    @ElementCollection
    private List<SeatInfo> seats;

}
