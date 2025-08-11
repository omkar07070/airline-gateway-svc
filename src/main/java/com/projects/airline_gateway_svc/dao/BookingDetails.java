package com.projects.airline_gateway_svc.dao;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "booking_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDetails {

    @Id
    @Column(name = "booking_id")
    private Integer bookingId;

    @Column(name = "t_id")
    private String tId;

    @Column(name = "username")
    private String username;

    @Column(name = "flight_name")
    private String flightName;

    @Column(name = "flight_id")
    private String flightId;

    @Column(name = "seat_no")
    private String seatNo;

    @Column(name = "seat_price")
    private BigDecimal seatPrice;

    @Column(name = "booking_date")
    private LocalDate bookingDate;
}