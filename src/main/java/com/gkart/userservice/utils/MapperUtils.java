package com.gkart.userservice.utils;

import com.gkart.userservice.model.RegisterRequest;
import com.gkart.userservice.model.UserDao;
import com.gkart.userservice.model.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;

@Component
public class MapperUtils {

    public static final String GKART_USER_SERVICE = "gkart-user-service";

    private static final Set<String> ROLES = Set.of("user");

    private PasswordEncoder passwordEncoder;

    public MapperUtils(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto userDaoToUserDtoMapper(UserDao userDao) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDao, userDto);
        return userDto;
    }

    public UserDao createUserDaoFromRequest(RegisterRequest registerRequest) {
        Date now = new Date();
        return UserDao.builder()
                .username(registerRequest.getEmail())
                .password(
                        passwordEncoder.encode(registerRequest.getPassword())
                )
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .gender(registerRequest.getGender().toLowerCase())
                .email(registerRequest.getEmail().toLowerCase())
                .mobile(registerRequest.getMobile())
                .roles(ROLES)
                .address(registerRequest.getAddress())
                .isAccountActive(true)
                .createdAt(now)
                .createdBy(GKART_USER_SERVICE)
                .updatedAt(now)
                .updatedBy(GKART_USER_SERVICE)
                .build();
    }
}
