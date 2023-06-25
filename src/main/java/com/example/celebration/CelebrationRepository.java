package com.example.celebration;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

/**
 * Repozytorium do operacji na kolekcji wydarzeń Celebration w bazie danych MongoDB.
 */
public interface CelebrationRepository extends MongoRepository<Celebration, String> {

    /**
     * Metoda do wyszukiwania wydarzenia Celebration po nazwie wydarzenia.
     *
     * @param eventName nazwa wydarzenia
     * @return Optional z wydarzeniem Celebration o podanej nazwie
     */
    Optional<Celebration> findCelebrationByEventName(String eventName);

}
