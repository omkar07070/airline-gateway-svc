package com.projects.airline_gateway_svc.dao;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.projects.airline_gateway_svc.model.response.SeatInfo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "flight_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightDetails {
    @Id
    @Column(name = "flight_id", length = 255)
    private String flightId;

    @Column(name = "flight_name", length = 255, nullable = false)
    private String flightName;

    @Column(name = "flight_date", nullable = false)
    private LocalDate flightDate;

    @Column(name = "flight_takeoff_time", nullable = false)
    private LocalTime flightTakeoffTime;

    @Column(name = "flight_landing_time", nullable = false)
    private LocalTime flightLandingTime;

    @Column(name = "flight_departure", length = 255, nullable = false)
    private String flightDeparture;

    @Column(name = "flight_destination", length = 255, nullable = false)
    private String flightDestination;

    // Store JSON array as List<SeatInfo>
    @ElementCollection
    @CollectionTable(name = "flight_seats", joinColumns = @JoinColumn(name = "flight_id"))
    private List<SeatInfo> seats;
}
