package com.example.celebration.controllers;

import com.example.celebration.dto.UserDTO;
import com.example.celebration.services.auth.AuthService;
import jakarta.validation.Valid;
import com.example.celebration.dto.AuthenticationDTO;
import com.example.celebration.utils.UserAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor
@RestController
public class AuthenticationController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserAuthenticationProvider userAuthenticationProvider;

    /**
     * Endpoint do uwierzytelniania użytkownika.
     *
     * @param authenticateDto obiekt zawierający dane uwierzytelniające użytkownika
     * @return odpowiedź HTTP z danymi uwierzytelnionego użytkownika i tokenem uwierzytelniającym
     */
    @PostMapping("/api/authenticate")
    public ResponseEntity<UserDTO> login(@RequestBody @Valid AuthenticationDTO authenticateDto) {
        UserDTO userDto = authService.login(authenticateDto);
        userDto.setToken(userAuthenticationProvider.createToken(userDto.getName()));
        return ResponseEntity.ok(userDto);
    }


}
