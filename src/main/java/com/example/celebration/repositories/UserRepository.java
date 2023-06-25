package com.example.celebration.repositories;

import com.example.celebration.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    /**
     * Znajduje użytkownika na podstawie adresu email.
     *
     * @param email adres email użytkownika
     * @return znaleziony użytkownik lub null, jeśli nie znaleziono
     */
    User findFirstByEmail(String email);

    /**
     * Znajduje użytkownika na podstawie nazwy.
     *
     * @param name nazwa użytkownika
     * @return znaleziony użytkownik lub null, jeśli nie znaleziono
     */
    User findFirstByName(String name);
}
