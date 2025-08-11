package com.projects.airline_gateway_svc.controller;

import com.projects.airline_gateway_svc.model.request.FlightDetailsRequest;
import com.projects.airline_gateway_svc.model.request.SignUpRequest;
import com.projects.airline_gateway_svc.model.response.FlightDetailsResponse;
import com.projects.airline_gateway_svc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/airline/gateway")
public class GatewayController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/sign_up")
public ResponseEntity<?> signUp(@RequestBody SignUpRequest signUpRequest){
   log.info("Inside signup controller");
   return userService.saveUserDetails(signUpRequest);

}

@PostMapping(value = "/fetchflightdetails")
    public FlightDetailsResponse fetchFlightDetails(@RequestBody FlightDetailsRequest flightDetailsRequest){

        return userService.fetchFlights(flightDetailsRequest);

    }
}
