package com.projects.airline_gateway_svc.dao;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "seat_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatDetails {

    @Id
    @Column(name = "seat_no")
    private String seatNo;

    @Column(name = "seat_type")
    private String seatType;

    @Column(name = "seat_price")
    private BigDecimal seatPrice;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private FlightDetails flight;

    @Column(name = "flight_name")
    private String flightName;

    @Column(name = "seat_status")
    private String seatStatus;
}