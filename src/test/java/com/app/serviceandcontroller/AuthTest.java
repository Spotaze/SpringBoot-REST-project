package com.app.serviceandcontroller;

import com.app.dto.SignupRequest;
import com.app.model.User;
import com.app.repository.UserRepo;
import com.app.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class AuthTest {

    @Autowired
    private AuthService authService;
    @MockBean
    private UserRepo userRepo;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void signup() {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername("test");
        signupRequest.setEmail("test@gmail.com");
        signupRequest.setPassword("test");

        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(signupRequest.getPassword());

        when(userRepo.save(user)).thenReturn(user);
        authService.signup(signupRequest);
    }

    @Test
    void login() {
    }
}