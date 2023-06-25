package com.example.celebration.services.auth;

import com.example.celebration.dto.AuthenticationDTO;
import com.example.celebration.dto.SignupDTO;
import com.example.celebration.dto.UserDTO;

public interface AuthService {
    /**
     * Tworzy nowego użytkownika na podstawie danych rejestracyjnych.
     *
     * @param signupDTO dane rejestracyjne użytkownika
     * @return obiekt UserDTO reprezentujący nowo utworzonego użytkownika
     */
    UserDTO createUser(SignupDTO signupDTO);

    /**
     * Loguje użytkownika na podstawie danych uwierzytelniających.
     *
     * @param authenticateDto dane uwierzytelniające użytkownika
     * @return obiekt UserDTO reprezentujący zalogowanego użytkownika
     *  @throws Error w przypadku nieprawidłowego loginu lub hasła
     */
    UserDTO login(AuthenticationDTO authenticateDto);
}
