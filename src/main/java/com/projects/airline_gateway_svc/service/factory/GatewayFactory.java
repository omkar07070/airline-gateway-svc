package com.projects.airline_gateway_svc.service.factory;

import com.projects.airline_gateway_svc.dao.SeatDetails;
import com.projects.airline_gateway_svc.dao.UserDetails;
import com.projects.airline_gateway_svc.dao.entity.FlightDetailsRepository;
import com.projects.airline_gateway_svc.dao.entity.SeatDetailsRepository;
import com.projects.airline_gateway_svc.dao.entity.UserDetailsRepository;
import com.projects.airline_gateway_svc.model.request.BookFlightRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

@Slf4j
@Service
public class GatewayFactory {

    @Autowired
    SeatDetailsRepository seatDetailsRepository;

    @Autowired
    UserDetailsRepository userDetailsRepository;

    public void validateRequest(BookFlightRequest bookFlightRequest){
        if(ObjectUtils.isEmpty(bookFlightRequest)){
            throw new RuntimeException("Empty booking request");
        }

        String seat = bookFlightRequest.getSeatno();
        SeatDetails seatDetails = seatDetailsRepository.findBySeatNo(seat);
        if(seatDetails.getSeatStatus().equals("BOOKED")){
             throw new RuntimeException("This seat is booked please choose another seat ");
        }
        validateUser(bookFlightRequest.getUserName());
        if(!(seatDetails.getSeatPrice()).equals(bookFlightRequest.getAmount())){
            log.error("Invalid amount entered ");
            throw new RuntimeException("Invalid credentials entered" );
        }

    }

    public void validateUser(String name){
       UserDetails userDetails =  userDetailsRepository.findByName(name);
       if(ObjectUtils.isEmpty(userDetails)){
           throw new NullPointerException("User not registered");
       }
    }
}
