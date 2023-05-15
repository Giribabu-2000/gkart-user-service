package com.gkart.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private String userId;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String gender;

    private String email;

    private String mobile;

    private Set<String> roles;

    private Address address;

    private Boolean isAccountActive;

    private Date createdAt;

    private String createdBy;

    private Date updatedAt;

    private String updatedBy;
}
