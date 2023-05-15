package com.gkart.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotNull
    @Pattern(
            regexp = "([a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z0-9_-]+)",
            message = "Invalid Email"
    )
    private String email;

    @NotNull
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password should contain at least one uppercase letter, lowercase letter, special character, digit and size must be greater than or equal to 8"
    )
    private String password;

    @NotBlank
    @NotNull
    private String firstName;

    @NotBlank
    @NotNull
    private String lastName;

    @NotNull
    @Pattern(
            regexp = "^(male|female|others)$",
            flags = Pattern.Flag.CASE_INSENSITIVE,
            message = "Invalid Gender"
    )
    private String gender;

    @NotNull
    @Pattern(
            regexp = "^(?:\\+?91|0)?[6789]\\d{9}$",
            message = "Invalid Mobile Number"
    )
    private String mobile;

    @NotNull
    @Valid
    private Address address;
}
