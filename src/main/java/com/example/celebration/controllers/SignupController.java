package com.example.celebration.controllers;

import com.example.celebration.dto.SignupDTO;
import com.example.celebration.dto.UserDTO;
import com.example.celebration.services.auth.AuthService;
import com.example.celebration.utils.UserAuthenticationProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SignupController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserAuthenticationProvider userAuthenticationProvider;

    /**
     * Endpoint do rejestracji nowego użytkownika.
     *
     * @param signupDTO obiekt zawierający dane rejestracyjne użytkownika
     * @return odpowiedź HTTP z danymi zarejestrowanego użytkownika i tokenem uwierzytelniającym
     */
    @PostMapping("/api/signup")
    public ResponseEntity<UserDTO> signupUser(@RequestBody @Valid SignupDTO signupDTO){
        UserDTO createdUser = authService.createUser(signupDTO);
        String token = userAuthenticationProvider.createToken(createdUser.getName());
        createdUser.setToken(token);
        return ResponseEntity.ok(createdUser);
    }


}
