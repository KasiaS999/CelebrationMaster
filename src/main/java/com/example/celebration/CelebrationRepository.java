package com.example.celebration;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface CelebrationRepository extends MongoRepository<Celebration, String> {

    Optional<Celebration> findCelebrationByEventName(String eventName);

}
