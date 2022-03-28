package com.ridhimakohli.hotelreservation.dao;

import com.ridhimakohli.hotelreservation.types.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

}
