package com.example.celebration.services.auth;

import com.example.celebration.User;
import com.example.celebration.dto.AuthenticationDTO;
import com.example.celebration.dto.SignupDTO;
import com.example.celebration.dto.UserDTO;
import com.example.celebration.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO createUser(SignupDTO signupDTO) {
        User user = new User();
        user.setName(signupDTO.getName());
        user.setEmail(signupDTO.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(signupDTO.getPassword()));
        User createdUser = userRepository.save(user);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(createdUser.getId());
        userDTO.setEmail(createdUser.getEmail());
        userDTO.setName(createdUser.getName());
        return userDTO;
    }

    @Override
    public UserDTO login(AuthenticationDTO authenticateDto) {
        User user = userRepository.findFirstByEmail(authenticateDto.getEmail());
        if (user == null){
            throw new Error("Nieprawidłowy login lub hasło");
        }
        if (passwordEncoder.matches(CharBuffer.wrap(authenticateDto.getPassword()), user.getPassword())) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setEmail(user.getEmail());
            userDTO.setName(user.getName());
            return userDTO;
        }
        throw new Error("Nieprawidłowy login lub hasło");
    }



}
