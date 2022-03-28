package com.ridhimakohli.hotelreservation.dao;

import com.ridhimakohli.hotelreservation.types.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
