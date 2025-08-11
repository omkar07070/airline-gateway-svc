package com.projects.airline_gateway_svc.model.response;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@Embeddable
public class SeatInfo {
    private String seatType;
    private int seatPrice;
    private int noOfSeats;
}
