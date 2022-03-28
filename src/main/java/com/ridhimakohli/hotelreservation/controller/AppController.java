package com.ridhimakohli.hotelreservation.controller;

import com.ridhimakohli.hotelreservation.dao.ReservationRepository;
import com.ridhimakohli.hotelreservation.dao.RoomRepository;
import com.ridhimakohli.hotelreservation.dao.UserRepository;
import com.ridhimakohli.hotelreservation.service.UserSessionService;
import com.ridhimakohli.hotelreservation.types.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class AppController {
    private static final DecimalFormat df = new DecimalFormat("0.00");

    @Autowired
    UserRepository userRepo;

    @Autowired
    RoomRepository roomRepository;
    
    @Autowired
    UserSessionService userSessionService;

    @Autowired
    ReservationRepository reservationRepository;
    
    @GetMapping("")
    public String viewHomePage(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("auth", auth);
        model.addAttribute("sessionId", userSessionService.getUserSessionId());
        return "index";

    }

    @GetMapping("/home")
    public String homeView(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("auth", auth);
        return "index";
    }

    @GetMapping("/about")
    public String aboutUsView(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("auth", auth);
        return "about_us";
    }

    @GetMapping("/dining")
    public String dinningView(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("auth", auth);
        return "dining";
    }

    @GetMapping("/attraction")
    public String attractionView(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("auth", auth);
        return "attraction";
    }

    @GetMapping(value="/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response, Model model) {
        return "redirect:/";
    }

    @GetMapping(value = "/")
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("auth", auth);
        return "index";
    }

    @GetMapping("/details")
    public  String processConfirm(@RequestParam Map<String, String> reqParam , Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.findByEmail(reqParam.get("email"));
        Room room = roomRepository.getById(Long.valueOf(reqParam.get("roomId")));
        String checkInDate  = reqParam.get("checkInDate");
        String checkOutDate  = reqParam.get("checkOutDate");

        Set<String> amenities = room.getAmenities().stream()
                .map(s -> s.toString())
                .map(AppController::toCamelCase)
                .collect(Collectors.toSet());
        String amenitiesInStr = String.join(", ", amenities);

        long noOfNights = ChronoUnit.DAYS.between(LocalDate.parse(checkInDate), LocalDate.parse(checkOutDate));
        double roomTotal = noOfNights*room.getPrice();
        double tax = Double.valueOf(df.format(roomTotal*7/100));
        double total = roomTotal + tax;

        Amount amount = new Amount(room.getPrice(), noOfNights, roomTotal , tax, total);
        Details details = new Details(checkInDate, checkOutDate, reqParam.get("people"), room, user, amount, amenitiesInStr);

        model.addAttribute("details", details);
        model.addAttribute("auth", auth);
        return "reservation";
    }

    @GetMapping(value = "/confirm")
    public String confirm(@RequestParam @NotBlank String confirmationId, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("auth", auth);
        final Reservation reservation = reservationRepository.getById(Long.valueOf(confirmationId));
        final Room room = roomRepository.getById(reservation.getRoomId());
        model.addAttribute("roomName", room.getRoomName());
        model.addAttribute("checkInDate", reservation.getCheckInDate());
        model.addAttribute("checkOutDate", reservation.getCheckOutDate());
        model.addAttribute("people", reservation.getPeople());
        model.addAttribute("confirmationNumber", reservation.getConfirmationNumber());
        return "confirm";
    }

    static String toCamelCase(String s){
        String[] parts = s.split("_");
        String camelCaseString = "";
        for (String part : parts){
            camelCaseString = camelCaseString + " " +  toProperCase(part);
        }
        return camelCaseString;
    }

    static String toProperCase(String s) {
        return s.substring(0, 1).toUpperCase() +
                s.substring(1).toLowerCase();
    }


}
