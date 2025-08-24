package com.projects.airline_gateway_svc.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projects.airline_gateway_svc.dao.FlightDetails;
import com.projects.airline_gateway_svc.dao.UserDetails;
import com.projects.airline_gateway_svc.dao.entity.FlightDetailsRepository;
import com.projects.airline_gateway_svc.model.request.BookFlightRequest;
import com.projects.airline_gateway_svc.model.request.FlightDetailsRequest;
import com.projects.airline_gateway_svc.model.request.SignUpRequest;
import com.projects.airline_gateway_svc.dao.entity.UserDetailsRepository;
import com.projects.airline_gateway_svc.model.response.BookFlightResponse;
import com.projects.airline_gateway_svc.model.response.FlightDetailsResponse;
import com.projects.airline_gateway_svc.model.response.PaymentMessage;
import com.projects.airline_gateway_svc.model.response.SeatInfo;
import com.projects.airline_gateway_svc.service.BookingService;
import com.projects.airline_gateway_svc.service.GatewayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.ResourceAccessException;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class GatewayServiceImpl implements GatewayService {

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    FlightDetailsRepository flightDetailsRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
BookingService bookingService;

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

        FlightDetails flightDetails = flightDetailsRepository
                .findByDateAndFlightDepartureAndFlightDestination(
                        flightDetailsRequest.getDate(),
                        flightDetailsRequest.getFlightTakeoff(),
                        flightDetailsRequest.getFlightLanding()
                );

        if (flightDetails == null) {
            throw new ResourceAccessException("No flight found for given details");
        }

        FlightDetailsResponse flightDetailsResponse = new FlightDetailsResponse();
        flightDetailsResponse.setFlightDate(flightDetails.getFlightDate());
        flightDetailsResponse.setFlightDestination(flightDetails.getFlightDestination());
        flightDetailsResponse.setFlightDeparture(flightDetails.getFlightDeparture());
        flightDetailsResponse.setFlightName(flightDetails.getFlightName());
        flightDetailsResponse.setFlightId(flightDetails.getFlightId());
        flightDetailsResponse.setFlightLandingTime(flightDetails.getFlightLandingTime());
        flightDetailsResponse.setFlightTakeoffTime(flightDetails.getFlightTakeoffTime());

        try {
            // Use TypeReference to inform Jackson about the generic List<Seat> type
            List<SeatInfo> seatList = objectMapper.readValue(flightDetails.getSeats(), new TypeReference<List<SeatInfo>>() {});
            flightDetailsResponse.setSeats(seatList);
        } catch (Exception e) {
            // Handle potential JSON parsing errors, e.g., log the error
            // For now, we set an empty list as a fallback.
            e.printStackTrace();
            flightDetailsResponse.setSeats(Collections.emptyList());
        }

        return flightDetailsResponse;
    }

    @Override
    public BookFlightResponse bookFlight(BookFlightRequest bookFlightRequest) {
        PaymentMessage paymentMessage = setNotificationMessage(bookFlightRequest);

       return  bookingService.bookflight(paymentMessage);

    }



    private PaymentMessage setNotificationMessage(BookFlightRequest bookFlightRequest) {
        PaymentMessage paymentMessage = new PaymentMessage();
        paymentMessage.setEmail(bookFlightRequest.getEmail());
        paymentMessage.setUsername(bookFlightRequest.getUserName());
        paymentMessage.setFlightName(bookFlightRequest.getFlightName());
        paymentMessage.setSeatNumber(bookFlightRequest.getSeatno());
        paymentMessage.setMobileNumber(bookFlightRequest.getMobileNumber());
        paymentMessage.setUserId(bookFlightRequest.getUserId());
//        String transactionId = bookFlightRequest.getFlightId().substring(1, bookFlightRequest.getFlightId().length() - 1) + bookFlightRequest.getDate() + bookFlightRequest.getSeatno();
//        paymentMessage.setTransactionId(transactionId);
        paymentMessage.setAmount(bookFlightRequest.getAmount());

        return paymentMessage;

    }

//    public void validateUser(String userName){
//        UserDetails userDetails = userDetailsRepository.findByName(userName);
//        if(userDetails == null){
//            throw new Exception );
//        }
//    }



}
