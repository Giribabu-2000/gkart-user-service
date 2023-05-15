package com.gkart.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {

    @NotNull
    @NotBlank
    private String doorNo;

    @NotNull
    @NotBlank
    private String street;

    @NotNull
    @NotBlank
    private String locality;

    @NotNull
    @NotBlank
    private String district;

    @NotNull
    @NotBlank
    private String state;

    @NotNull
    @Pattern(
            regexp = "^\\d{6}$",
            message = "Invalid Pin Code"
    )
    private String pinCode;
}
