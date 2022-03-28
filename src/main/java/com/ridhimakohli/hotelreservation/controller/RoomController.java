package com.ridhimakohli.hotelreservation.controller;

import com.ridhimakohli.hotelreservation.service.RoomService;
import com.ridhimakohli.hotelreservation.types.AjaxResponseBody;
import com.ridhimakohli.hotelreservation.types.Room;
import com.ridhimakohli.hotelreservation.types.external.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RoomController {

    @Autowired
    RoomService roomService;


    @PostMapping("/api/search")
    public ResponseEntity<?> getSearchResultViaAjax(
            @Valid @RequestBody SearchRequest searchCriteria, Errors errors) {

        AjaxResponseBody<Room> result = new AjaxResponseBody();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            result.setMsg(errors.getAllErrors()
                    .stream().map(x -> x.getDefaultMessage())
                    .collect(Collectors.joining(",")));

            return ResponseEntity.badRequest().body(result);

        }

        List<Room> rooms = roomService.getAllRooms("price",  searchCriteria);
        if (rooms.isEmpty()) {
            result.setMsg("no rooms found!");
        } else {
            result.setMsg("success");
        }
        result.setResult(rooms);
        result.setAuth(auth);

        return ResponseEntity.ok(result);

    }
}
