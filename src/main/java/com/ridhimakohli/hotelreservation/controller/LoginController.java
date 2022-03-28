package com.ridhimakohli.hotelreservation.controller;

import com.ridhimakohli.hotelreservation.dao.UserRepository;
import com.ridhimakohli.hotelreservation.types.AjaxResponseBody;
import com.ridhimakohli.hotelreservation.types.User;
import com.ridhimakohli.hotelreservation.types.external.LoginRequest;
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
public class LoginController {

    @Autowired
    UserRepository userRepo;

    @PostMapping("/api/login")
    public ResponseEntity<?> loginUser(
            @Valid @RequestBody LoginRequest loginCriteria, Errors errors) {

        AjaxResponseBody<User> result = new AjaxResponseBody();
        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            result.setMsg(errors.getAllErrors()
                    .stream().map(x -> x.getDefaultMessage())
                    .collect(Collectors.joining(",")));

            return ResponseEntity.badRequest().body(result);

        }

        User user = userRepo.findByEmail(loginCriteria.getEmail());
        if (user == null) {
            result.setMsg("We don't have any account with this email, can you please create one using signup");
            return ResponseEntity.ok(result);
        } else {
            result.setMsg("success");
        }
        result.setResult(List.of(user));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        result.setAuth(auth);
        result.setKey(UUID.randomUUID().toString());
        return ResponseEntity.ok(result);

    }

    @PostMapping("/api/signup")
    public ResponseEntity<?> registrationUser(
            @Valid @RequestBody SignupRequest signupCriteria, Errors errors) {
        BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode((signupCriteria.getPassword()));

        AjaxResponseBody<User> result = new AjaxResponseBody();
        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            result.setMsg(errors.getAllErrors()
                    .stream().map(x -> x.getDefaultMessage())
                    .collect(Collectors.joining(",")));

            return ResponseEntity.badRequest().body(result);

        }
        User user = new User();
        user.setEmail(signupCriteria.getEmail());
        user.setPassword(encodedPassword);
        user.setFirstname(signupCriteria.getFirstName());
        user.setLastName(signupCriteria.getLastName());
        User savedUser = userRepo.save(user);
        if (savedUser.getId() == null) {
            result.setMsg("We are facing some issue while saving detials, please try again after sometime");
        } else {
            result.setMsg("Successfully created account");
        }
        result.setResult(List.of(user));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        result.setAuth(auth);
        return ResponseEntity.ok(result);

    }
}
