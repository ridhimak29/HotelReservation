package com.ridhimakohli.hotelreservation.types.external;

import javax.validation.constraints.NotBlank;

public class SignupRequest {

    @NotBlank(message = "email can't empty!")
    String email;

    @NotBlank(message = "password can't empty!")
    String password;


    @NotBlank(message = "firstName can't empty!")
    String firstName;

    @NotBlank(message = "lastName can't empty!")
    String lastName;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
