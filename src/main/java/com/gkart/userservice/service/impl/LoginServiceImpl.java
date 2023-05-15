package com.gkart.userservice.service.impl;

import com.gkart.userservice.model.LoginRequest;
import com.gkart.userservice.model.LoginResponse;
import com.gkart.userservice.model.UserDao;
import com.gkart.userservice.repository.UserRepository;
import com.gkart.userservice.service.LoginService;
import com.gkart.userservice.utils.JwtUtil;
import com.gkart.userservice.utils.MapperUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    private UserRepository userRepository;

    private MapperUtils mapperUtils;

    private PasswordEncoder passwordEncoder;

    private JwtUtil jwtUtil;

    public LoginServiceImpl(UserRepository userRepository, MapperUtils mapperUtils, PasswordEncoder passwordEncoder,
                            JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.mapperUtils = mapperUtils;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) throws Exception {

        UserDao userDao = userRepository.findByUsername(loginRequest.getUsername());

        if (Objects.isNull(userDao)
                || Objects.isNull(userDao.getUsername())
                || userDao.getUsername().isBlank()
                || !(passwordEncoder.matches(loginRequest.getPassword(), userDao.getPassword()))) {
            throw new BadCredentialsException(
                    "Incorrect Credentials"
            );
        }

        String token = jwtUtil.generateToken(userDao.getUsername(), userDao.getRoles());
        return new LoginResponse(token);
    }
}
