package com.projects.airline_gateway_svc.dao.entity;


import com.projects.airline_gateway_svc.dao.FlightDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface FlightDetailsRepository extends JpaRepository<FlightDetails,String> {

    @Query(value = "SELECT * FROM flight_details " +
            "WHERE flight_date = :date " +
            "AND flight_departure = :departure " +
            "AND flight_destination = :destination",
            nativeQuery = true)
    FlightDetails findByDateAndFlightDepartureAndFlightDestination(LocalDate date, String departure, String destination);
}
