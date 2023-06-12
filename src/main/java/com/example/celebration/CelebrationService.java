package com.example.celebration;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service
public class CelebrationService {

    private final CelebrationRepository celebrationRepository;

    public List<Celebration> getAllStudents() {
        return celebrationRepository.findAll();
    }

    public Celebration getCelebrationById(String id) {
        return celebrationRepository.findById(id).orElse(null);
    }

    public Celebration createCelebration(Celebration celebration) {
        return celebrationRepository.save(celebration);
    }

    public void deleteCelebrationById(String id) {
        celebrationRepository.deleteById(id);
    }

    public Celebration updateCelebration(String id, Celebration celebrationUpdates) {
        Optional<Celebration> optionalCelebration = celebrationRepository.findById(id);
        if (optionalCelebration.isPresent()) {
            Celebration celebration = optionalCelebration.get();
            celebration.setEventName(celebrationUpdates.getEventName());
            celebration.setEventDateTime(celebrationUpdates.getEventDateTime());
            celebration.setNotes(celebrationUpdates.getNotes());
            celebration.setMenuItems(celebrationUpdates.getMenuItems());
            celebration.setShoppingItems(celebrationUpdates.getShoppingItems());
            celebration.setGuests(celebrationUpdates.getGuests());
            celebration.setEventPlan(celebrationUpdates.getEventPlan());
            return celebrationRepository.save(celebration);
        }
        return null;
    }

    public Celebration findCelebrationByEventName(String eventName) {
        return celebrationRepository.findCelebrationByEventName(eventName).orElse(null);
    }
}
