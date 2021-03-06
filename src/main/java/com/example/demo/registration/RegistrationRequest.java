package com.example.demo.registration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private final String fullName;
    private final String userName;
    private final String email;
    private final String phoneNumber;
    private final String password;
    private final String gender;
    private final String dob;
    private final String imgUrl;
    private final String pinCode;
}
