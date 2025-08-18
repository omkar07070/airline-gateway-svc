//package com.projects.airline_gateway_svc.service.impl;
//
//import com.projects.airline_gateway_svc.dao.SeatDetails;
//import com.projects.airline_gateway_svc.model.request.BookFlightRequest;
//import com.projects.airline_gateway_svc.model.response.BookFlightResponse;
//import jakarta.transaction.Transactional;
//import org.springframework.stereotype.Service;
//
//@Service
//public class PaymentServiceImpl {
//
//
//    public BookFlightResponse bookFlight(BookFlightRequest request) {
//
//        // Step 1: Fail-fast check. Verify if the seat is available locally before any external calls.
//        if (!isSeatAvailable(request.getFlightId(), request.getSeatno())) {
//            return new BookFlightResponse("FAILED", "Seat is no longer available.", null, request.getFlightId(), request.getSeatno());
//        }
//
//        BookFlightResponse transactionResponse;
//        try {
//            // Step 2: Prepare the synchronous call to the external endpoint.
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//
//            TransactionRequest transactionRequest = new TransactionRequest(
//                    request.getFlightId(),
//                    request.getFlightName(),
//                    request.getSeatno(),
//                    request.getUserName()
//            );
//
//            HttpEntity<TransactionRequest> entity = new HttpEntity<>(transactionRequest, headers);
//
//            // Make the synchronous POST call and wait for the response.
//            ResponseEntity<BookFlightResponse> responseEntity = restTemplate.postForEntity(
//                    TRANSACTION_API_URL, entity, BookFlightResponse.class);
//
//            transactionResponse = responseEntity.getBody();
//
//            // Step 3: Process the response from the transaction service.
//            if (transactionResponse == null || !"SUCCESS".equalsIgnoreCase(transactionResponse.getResponse())) {
//                String errorMessage = (transactionResponse != null) ? transactionResponse.getResponse() : "Transaction endpoint returned a null response.";
//                return new BookingFlightResponse("FAILED", "Booking rejected by transaction service: " + errorMessage, null, request.getFlightId(), request.getSeatno());
//            }
//
//        } catch (RestClientException e) {
//            // Handle network or connection errors when calling the external service.
//            return new BookingFlightResponse("FAILED", "Could not connect to the transaction processing service. Please try again later.", null, request.getFlightId(), request.getSeatno());
//        }
//
//        // Step 4: If the external transaction was successful, update the local database.
//        try {
//            updateSeatStatusToBooked(request.getFlightId(), request.getSeatno());
//        } catch (IllegalStateException e) {
//            // This is a CRITICAL state. The transaction was approved, but we failed to update our local DB.
//            // This situation requires a compensating transaction (e.g., a refund or cancellation API call).
//            return new BookingFlightResponse("CRITICAL_ERROR",
//                    "Transaction was approved but failed to secure the seat locally. Please contact support.",
//                    transactionResponse.getTransactionId(), request.getFlightId(), request.getSeatno());
//        }
//
//        // Step 5: Return the final success response to the user.
//        return new BookingFlightResponse(
//                "SUCCESS",
//                "Flight booked successfully!",
//                transactionResponse.getTransactionId(), // The ID from the external service
//                request.getFlightId(),
//                request.getSeatno()
//        );
//    }
//
//    /**
//     * Checks the database to see if the seat is currently available.
//     */
//    private boolean isSeatAvailable(String flightId, String seatNo) {
//        return seatDetailsRepository.findByFlightIdAndSeatno(flightId, seatNo)
//                .map(seat -> "available".equalsIgnoreCase(seat.getSeatStatus()))
//                .orElse(false);
//    }
//
//    /**
//     * Updates the seat status to 'booked'. This method is transactional.
//     * It includes a final check to prevent race conditions.
//     */
//    @Transactional
//    public void updateSeatStatusToBooked(String flightId, String seatNo) {
//        SeatDetails seat = seatDetailsRepository.findByFlightIdAndSeatno(flightId, seatNo)
//                .orElseThrow(() -> new IllegalStateException("Seat disappeared from database during final update."));
//
//        // Final check to prevent race conditions.
//        if (!"available".equalsIgnoreCase(seat.getSeatStatus())) {
//            throw new IllegalStateException("Seat was booked by another user during transaction processing.");
//        }
//
//        seat.setSeatStatus("booked");
//        seatDetailsRepository.save(seat);
//    }
//}
