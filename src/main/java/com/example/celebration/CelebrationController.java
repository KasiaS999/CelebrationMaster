package com.example.celebration;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Kontroler obsługujący endpointy dotyczące wydarzeń Celebration.
 */
@RestController
@RequestMapping("api/v1/celebrations")
@AllArgsConstructor
public class CelebrationController {

    private final CelebrationService celebrationService;


    /**
     * Endpoint GET do pobierania wszystkich wydarzeń Celebration.
     *
     * @return lista wszystkich wydarzeń Celebration
     */
    @GetMapping
    public List<Celebration> fetchAllCelebrations(){
        return celebrationService.getAllCelebrations();
    }

    /**
     * Endpoint GET do pobierania wydarzenia Celebration o podanym identyfikatorze.
     *
     * @param id identyfikator wydarzenia
     * @return ResponseEntity z wydarzeniem Celebration lub status 404, jeśli wydarzenie nie zostało znalezione
     */
    @GetMapping("/{id}")
    public ResponseEntity<Celebration> getCelebrationById(@PathVariable String id) {
        Celebration celebration = celebrationService.getCelebrationById(id);
        if (celebration != null) {
            return ResponseEntity.ok(celebration);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Endpoint POST do tworzenia nowego wydarzenia Celebration.
     *
     * @param celebration obiekt wydarzenia Celebration
     * @return ResponseEntity z utworzonym wydarzeniem Celebration i statusem 201, jeśli utworzenie powiodło się
     */
    @PostMapping
    public ResponseEntity<Celebration> createCelebration(@RequestBody Celebration celebration) {
        Celebration createdCelebration = celebrationService.createCelebration(celebration);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCelebration);
    }

    /**
     * Endpoint PUT do aktualizacji wydarzenia Celebration o podanym identyfikatorze.
     *
     * @param id                identyfikator wydarzenia
     * @param celebrationUpdates obiekt z aktualizacjami dla wydarzenia Celebration
     * @return ResponseEntity z zaktualizowanym wydarzeniem Celebration lub status 404, jeśli wydarzenie nie zostało znalezione
     */
    @PutMapping("/{id}")
    public ResponseEntity<Celebration> updateCelebration(
            @PathVariable String id,
            @RequestBody Celebration celebrationUpdates
    ) {
        Celebration updatedCelebration = celebrationService.updateCelebration(id, celebrationUpdates);
        if (updatedCelebration != null) {
            return ResponseEntity.ok(updatedCelebration);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Endpoint DELETE do usuwania wydarzenia Celebration o podanym identyfikatorze.
     *
     * @param id identyfikator wydarzenia
     * @return ResponseEntity ze statusem 204
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCelebration(@PathVariable String id) {
        celebrationService.deleteCelebrationById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint GET do wyszukiwania wydarzenia Celebration po nazwie wydarzenia.
     *
     * @param eventName nazwa wydarzenia
     * @return ResponseEntity z wydarzeniem Celebration o podanej nazwie lub status 404, jeśli wydarzenie nie zostało znalezione
     */
    @GetMapping("/eventName/{eventName}")
    public ResponseEntity<Celebration> findCelebrationByEventName(@PathVariable String eventName) {
        Celebration celebration = celebrationService.findCelebrationByEventName(eventName);
        if (celebration != null) {
            return ResponseEntity.ok(celebration);
        }
        return ResponseEntity.notFound().build();
    }
}
