package com.projects.airline_gateway_svc.dao.entity;

import com.projects.airline_gateway_svc.dao.FlightDetails;
import com.projects.airline_gateway_svc.dao.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails,Integer> {

    UserDetails findByName(String userName);


}
