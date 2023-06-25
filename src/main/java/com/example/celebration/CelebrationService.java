package com.example.celebration;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * Serwis do obsługi operacji na wydarzeniach Celebration.
 */
@AllArgsConstructor
@Service
public class CelebrationService {


    private final CelebrationRepository celebrationRepository;

    /**
     * Pobiera wszystkie wydarzenia Celebration.
     *
     * @return lista wszystkich wydarzeń Celebration
     */
    public List<Celebration> getAllCelebrations() {
        return celebrationRepository.findAll();
    }

    /**
     * Pobiera wydarzenie Celebration o podanym identyfikatorze.
     *
     * @param id identyfikator wydarzenia
     * @return wydarzenie Celebration o podanym identyfikatorze lub null, jeśli nie istnieje
     */
    public Celebration getCelebrationById(String id) {
        return celebrationRepository.findById(id).orElse(null);
    }

    /**
     * Tworzy nowe wydarzenie Celebration.
     *
     * @param celebration wydarzenie Celebration do utworzenia
     * @return utworzone wydarzenie Celebration
     */
    public Celebration createCelebration(Celebration celebration) {
        return celebrationRepository.save(celebration);
    }

    /**
     * Usuwa wydarzenie Celebration o podanym identyfikatorze.
     *
     * @param id identyfikator wydarzenia do usunięcia
     */
    public void deleteCelebrationById(String id) {
        celebrationRepository.deleteById(id);
    }

    /**
     * Aktualizuje wydarzenie Celebration o podanym identyfikatorze.
     *
     * @param id                identyfikator wydarzenia do aktualizacji
     * @param celebrationUpdates obiekt Celebration z zaktualizowanymi danymi
     * @return zaktualizowane wydarzenie Celebration lub null, jeśli wydarzenie nie istnieje
     */
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

    /**
     * Wyszukuje wydarzenie Celebration po nazwie wydarzenia.
     *
     * @param eventName nazwa wydarzenia
     * @return wydarzenie Celebration o podanej nazwie lub null, jeśli nie istnieje
     */
    public Celebration findCelebrationByEventName(String eventName) {
        return celebrationRepository.findCelebrationByEventName(eventName).orElse(null);
    }
}
