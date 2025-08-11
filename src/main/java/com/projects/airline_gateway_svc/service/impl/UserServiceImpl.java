package com.projects.airline_gateway_svc.service.impl;

import com.projects.airline_gateway_svc.dao.FlightDetails;
import com.projects.airline_gateway_svc.dao.UserDetails;
import com.projects.airline_gateway_svc.dao.entity.FlightDetailsRepository;
import com.projects.airline_gateway_svc.model.request.FlightDetailsRequest;
import com.projects.airline_gateway_svc.model.request.SignUpRequest;
import com.projects.airline_gateway_svc.dao.entity.UserDetailsRepository;
import com.projects.airline_gateway_svc.model.response.FlightDetailsResponse;
import com.projects.airline_gateway_svc.model.response.SeatInfo;
import com.projects.airline_gateway_svc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    FlightDetailsRepository flightDetailsRepository;

    @Override
    public ResponseEntity<String> saveUserDetails(SignUpRequest signUpRequest) {
        log.info("Inside save user details ");
        UserDetails userDetails = new UserDetails();
        userDetails.setEmail(signUpRequest.getEmailId());
        userDetails.setDob(signUpRequest.getDob());
        userDetails.setName(signUpRequest.getFirstName()+" "+signUpRequest.getLastName());
        userDetails.setAccountNumber(signUpRequest.getAccountNumber());
        userDetails.setAccountBalance(signUpRequest.getAccBalance());
//        userDetails.setId();
        UserDetails userDetails1 = userDetailsRepository.findByName(userDetails.getName());
        if(!StringUtils.isEmpty(userDetails1)){
            return ResponseEntity.ok("User already exists");
        }
        else {
            userDetailsRepository.save(userDetails);
            return ResponseEntity.ok("User sign up done successfully ");
        }
    }

    @Override
    public FlightDetailsResponse fetchFlights(FlightDetailsRequest flightDetailsRequest) {

        FlightDetails flightDetails = flightDetailsRepository.findByDateAndFlightDepartureAndFlightDestination(flightDetailsRequest.getDate(),flightDetailsRequest.getFlightTakeoff(),flightDetailsRequest.getFlightLanding());
        FlightDetailsResponse flightDetailsResponse = new FlightDetailsResponse();
        flightDetailsResponse.setFlightDate(flightDetails.getFlightDate());
        flightDetailsResponse.setFlightDestination(flightDetails.getFlightDestination());
        flightDetailsResponse.setFlightDeparture(flightDetails.getFlightDeparture());
        flightDetailsResponse.setFlightName(flightDetails.getFlightName());
        flightDetailsResponse.setFlightId(flightDetails.getFlightId());

//        List<SeatInfo> seatList = new ArrayList<>();
//        for (SeatInfo seat : flightDetails.getSeats()) {
//            seatList.add(seat);
//        }
//        flightDetailsResponse.setSeats(seatList);
        return flightDetailsResponse;
    }


}
