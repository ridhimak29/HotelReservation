package com.ridhimakohli.hotelreservation.service;

import com.ridhimakohli.hotelreservation.dao.RoomRepository;
import com.ridhimakohli.hotelreservation.types.Room;
import com.ridhimakohli.hotelreservation.types.external.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {

    @Autowired
    RoomRepository roomRepository;

    public List<Room> getAllRooms(String sortBy, SearchRequest searchCriteria)
    {
        Pageable paging = PageRequest.of(0, 5, Sort.by(sortBy));

        Page<Room> pagedResult = roomRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }

}
