package com.kodilla.restaurantfrontend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee<DateTimeField> {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private DateTimeField hireDate;
    private String login;
    private String password;
    private String PIN;
}
