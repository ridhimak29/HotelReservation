package com.ridhimakohli.hotelreservation.dao;

import com.ridhimakohli.hotelreservation.types.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {
}
