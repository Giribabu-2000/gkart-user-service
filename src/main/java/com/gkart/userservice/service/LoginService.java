package com.gkart.userservice.service;

import com.gkart.userservice.model.LoginRequest;
import com.gkart.userservice.model.LoginResponse;

public interface LoginService {

    LoginResponse login(LoginRequest loginRequest) throws Exception;
}
