package com.gkart.userservice.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gkart.userservice.model.Address;
import com.gkart.userservice.model.LoginRequest;
import com.gkart.userservice.model.RegisterRequest;
import com.gkart.userservice.repository.UserRepository;
import com.gkart.userservice.service.LoginService;
import com.gkart.userservice.service.RegisterService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RegisterService registerService;

    @MockBean
    private LoginService loginService;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static RegisterRequest registerRequest;

    private static Address address;

    private static UserResponse userResponse;

    private static LoginRequest loginRequest;

    @BeforeAll
    public static void setup() {
        address = new Address(
                "1/1",
                "Test_1 Street",
                "Area1",
                "District1",
                "State1",
                "600001"
        );
        registerRequest = new RegisterRequest(
                "test@mail.com",
                "Test@123",
                "Test",
                "User",
                "others",
                "9898989898",
                address
        );
        userResponse = new UserResponse(
                registerRequest.getEmail(),
                registerRequest.getFirstName(),
                registerRequest.getLastName(),
                registerRequest.getGender().toLowerCase(),
                registerRequest.getEmail(),
                registerRequest.getMobile(),
                address
        );
        loginRequest = new LoginRequest(
                "test@mail.com",
                "Test@123"
        );
    }

    @Test
    void registerUserTest() throws Exception {
        when(registerService.register(registerRequest)).thenReturn(userResponse);
        String registerRequest1String = OBJECT_MAPPER.writeValueAsString(registerRequest);
        this.mockMvc
                .perform(
                    post("/register")
                            .content(registerRequest1String)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(
                        containsString(OBJECT_MAPPER.writeValueAsString(userResponse))
                ));
    }

    @Test
    void loginTest() throws Exception {
        when(loginService.login(loginRequest)).thenReturn(userResponse);
        String loginRequestString = OBJECT_MAPPER.writeValueAsString(loginRequest);
        this.mockMvc
                .perform(
                    post("/login")
                            .content(loginRequestString)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(
                        containsString(OBJECT_MAPPER.writeValueAsString(userResponse))
                ));
    }


}
