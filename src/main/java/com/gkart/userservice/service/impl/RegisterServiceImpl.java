package com.gkart.userservice.service.impl;

import com.gkart.userservice.exception.CustomException;
import com.gkart.userservice.model.RegisterRequest;
import com.gkart.userservice.model.UserDao;
import com.gkart.userservice.repository.UserRepository;
import com.gkart.userservice.service.RegisterService;
import com.gkart.userservice.utils.MapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RegisterServiceImpl implements RegisterService {

    private static final String SUCCESS_MESSAGE = "Registration Successful";

    private UserRepository userRepository;

    private MapperUtils mapperUtils;

    public RegisterServiceImpl(
            UserRepository userRepository,
            MapperUtils mapperUtils
    ) {
        this.userRepository = userRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public String register(RegisterRequest registerRequest) {
        checkEmailAndMobile(registerRequest.getEmail(), registerRequest.getMobile());
        UserDao userDao = mapperUtils.createUserDaoFromRequest(registerRequest);
        userDao = userRepository.save(userDao);
        log.info("User Registered: {}", userDao);
        return SUCCESS_MESSAGE;
    }

    private void checkEmailAndMobile(String email, String mobile) {
        if (userRepository.existsByEmail(email)) {
            log.warn("Registration Failed: Given Email Id - {} is already registered", email);
            throw new CustomException(
                    "Email Id is already registered.",
                    HttpStatus.CONFLICT
            );
        } else if (userRepository.existsByMobile(mobile)) {
            log.warn("Registration Failed: Given Mobile Number - {} is already registered", mobile);
            throw new CustomException(
                    "Given mobile number is already registered.",
                    HttpStatus.CONFLICT
            );
        }
    }

}
