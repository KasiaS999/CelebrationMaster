package com.example.celebration.controllers;


import com.example.celebration.dto.SignupDTO;
import com.example.celebration.dto.UserDTO;
import com.example.celebration.services.auth.AuthService;
import com.example.celebration.utils.UserAuthenticationProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SignupControllerTest {

    @Mock
    private AuthService authService;

    @Mock
    private UserAuthenticationProvider userAuthenticationProvider;

    @InjectMocks
    private SignupController signupController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSignupUser() {
        SignupDTO signupDTO = new SignupDTO();
        signupDTO.setName("aaa");
        signupDTO.setEmail("aaa@aaa");
        signupDTO.setPassword("aaa");
        UserDTO createdUser = new UserDTO();


        when(authService.createUser(signupDTO)).thenReturn(createdUser);
        when(userAuthenticationProvider.createToken(createdUser.getName())).thenReturn(createdUser.getToken());

        ResponseEntity<UserDTO> response = signupController.signupUser(signupDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(createdUser, response.getBody());
        verify(authService, times(1)).createUser(signupDTO);
        verify(userAuthenticationProvider, times(1)).createToken(createdUser.getName());
    }
}