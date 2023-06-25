package com.example.celebration.controllers;

import com.example.celebration.dto.AuthenticationDTO;
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

class AuthenticationControllerTest {
    @Mock
    private AuthService authService;

    @Mock
    private UserAuthenticationProvider userAuthenticationProvider;

    @InjectMocks
    private AuthenticationController authenticationController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login() {
        AuthenticationDTO authenticateDto = new AuthenticationDTO();
        authenticateDto.setEmail("aaa@aaa");
        authenticateDto.setPassword("aaa");
        UserDTO userDto = new UserDTO();

        when(authService.login(authenticateDto)).thenReturn(userDto);
        when(userAuthenticationProvider.createToken(userDto.getName())).thenReturn(userDto.getToken());

        ResponseEntity<UserDTO> response = authenticationController.login(authenticateDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDto, response.getBody());
        verify(authService, times(1)).login(authenticateDto);
        verify(userAuthenticationProvider, times(1)).createToken(userDto.getName());
    }
}