package com.gkart.userservice.controller;

import com.gkart.userservice.model.LoginRequest;
import com.gkart.userservice.model.LoginResponse;
import com.gkart.userservice.model.RegisterRequest;
import com.gkart.userservice.service.LoginService;
import com.gkart.userservice.service.RegisterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
public class UserController {

    private RegisterService registerService;

    private LoginService loginService;

    public UserController(RegisterService registerService, LoginService loginService) {
        this.registerService = registerService;
        this.loginService = loginService;
    }

    @Operation(
            summary = "User Registration",
            description = "This API endpoint is used for User Registration"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successful User Registration"),
                    @ApiResponse(responseCode = "400", description = "Registration Request is not in the correct format"),
                    @ApiResponse(responseCode = "409", description = "Email or Phone number is already registered"),
                    @ApiResponse(responseCode = "500", description = "Server Error")
            }
    )
    @PostMapping("/register")
    public String register(@RequestBody @Valid RegisterRequest registerRequest) throws Exception {
        return registerService.register(registerRequest);
    }

    @Operation(
            summary = "User Login",
            description = "This API endpoint is used for User Login"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successful User Login"),
                    @ApiResponse(responseCode = "400", description = "Login Request is not in the correct format"),
                    @ApiResponse(responseCode = "401", description = "Invalid Credentials"),
                    @ApiResponse(responseCode = "500", description = "Server Error")
            }
    )
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) throws Exception {
        return loginService.login(loginRequest);
    }

}
