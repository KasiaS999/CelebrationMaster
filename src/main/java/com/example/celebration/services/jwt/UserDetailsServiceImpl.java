package com.example.celebration.services.jwt;


import com.example.celebration.User;
import com.example.celebration.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Ładuje dane użytkownika na podstawie podanei nazwy użytkownika.
     *
     * @param name nazwa użytkownika
     * @return obiekt UserDetails reprezentujący użytkownika
     * @throws UsernameNotFoundException jeśli użytkownik o podanym imieniu nie został znaleziony
     */
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.findFirstByName(name);
        if(user == null){
            throw new UsernameNotFoundException("User not found",null);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
