package com.projects.airline_gateway_svc.service;

import com.projects.airline_gateway_svc.model.request.BookFlightRequest;
import com.projects.airline_gateway_svc.model.request.FlightDetailsRequest;
import com.projects.airline_gateway_svc.model.request.SignUpRequest;
import com.projects.airline_gateway_svc.model.response.BookFlightResponse;
import com.projects.airline_gateway_svc.model.response.FlightDetailsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface GatewayService {
    public ResponseEntity<String> saveUserDetails(SignUpRequest signUpRequest);

    public FlightDetailsResponse fetchFlights(FlightDetailsRequest flightDetailsRequest);

    public BookFlightResponse bookFlight(BookFlightRequest bookFlightRequest);
}
