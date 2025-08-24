package com.projects.airline_gateway_svc.dao.entity;

import com.projects.airline_gateway_svc.dao.SeatDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatDetailsRepository extends JpaRepository<SeatDetails,String> {
    public SeatDetails findBySeatNo(String seatNo);
}
