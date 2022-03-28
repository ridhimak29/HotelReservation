package com.ridhimakohli.hotelreservation.controller;

import com.ridhimakohli.hotelreservation.dao.GuestRepository;
import com.ridhimakohli.hotelreservation.dao.PaymentRepository;
import com.ridhimakohli.hotelreservation.dao.ReservationRepository;
import com.ridhimakohli.hotelreservation.types.AjaxResponseBody;
import com.ridhimakohli.hotelreservation.types.Guest;
import com.ridhimakohli.hotelreservation.types.Payment;
import com.ridhimakohli.hotelreservation.types.Reservation;
import com.ridhimakohli.hotelreservation.types.external.ReservationRequest;
import com.ridhimakohli.hotelreservation.types.external.SignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class ReservationController {

    @Autowired
    GuestRepository guestRepository;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @PostMapping("/api/reservation")
    public ResponseEntity<?> registrationUser(
            @Valid @RequestBody ReservationRequest reservationRequest, Errors errors) {
        AjaxResponseBody<Reservation> result = new AjaxResponseBody();
        long customerId = Long.valueOf(reservationRequest.getCustomerId());
        long roomId = Long.valueOf(reservationRequest.getRoomId());
        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            result.setMsg(errors.getAllErrors()
                    .stream().map(x -> x.getDefaultMessage())
                    .collect(Collectors.joining(",")));

            return ResponseEntity.badRequest().body(result);
        }

        Guest savedGuest = guestRepository.save(reservationRequest.getGuest());
        Payment savedPayment = paymentRepository.save(reservationRequest.getPayment());
        Reservation reservation = new Reservation(UUID.randomUUID().toString(), customerId, roomId,
                savedGuest.getId(), savedPayment.getId(), reservationRequest.getCheckInDate(),
                reservationRequest.getCheckOutDate(), Integer.valueOf(reservationRequest.getPeople()));

        Reservation savedReservation = reservationRepository.save(reservation);

        if (savedReservation == null) {
            result.setMsg("Something went wrong while confirming reservation, Please try again");
        } else {
            result.setMsg("success");
        }
        result.setResult(List.of(savedReservation));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        result.setAuth(auth);
        return ResponseEntity.ok(result);
    }
}
