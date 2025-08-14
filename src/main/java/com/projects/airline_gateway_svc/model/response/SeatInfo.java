package com.projects.airline_gateway_svc.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
public class SeatInfo {
    @JsonProperty("seat_type") // Maps the JSON key "seat_type" to this field
    private String seatType;

    @JsonProperty("seat_price") // Maps the JSON key "seat_price" to this field
    private BigDecimal seatPrice;

    // As seen in your debugger, the Java field is 'noOfSeats'
    // while the JSON key is 'total_seats'
    @JsonProperty("total_seats") // Maps the JSON key "total_seats" to this field
    private int noOfSeats;
}
