package com.projects.airline_gateway_svc.service;

import com.projects.airline_gateway_svc.model.response.BookFlightResponse;
import com.projects.airline_gateway_svc.model.response.PaymentMessage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@AllArgsConstructor
@Service
public class BookingService {

    private final WebClient webClient;

    public BookFlightResponse bookflight(PaymentMessage bookFlightRequest) {
        String finalUrl = "http://localhost:8282/airline/payment/bookseat";
        return webClient.post()
                .uri(finalUrl)
                .bodyValue(bookFlightRequest)
                .retrieve()
                .bodyToMono(BookFlightResponse.class)
                .block();
    }
}