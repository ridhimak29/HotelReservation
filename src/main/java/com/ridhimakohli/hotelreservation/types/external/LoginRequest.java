package com.ridhimakohli.hotelreservation.types.external;

import javax.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank(message = "email can't empty!")
    String email;

    @NotBlank(message = "password can't empty!")
    String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
