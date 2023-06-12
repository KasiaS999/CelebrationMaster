package com.example.celebration;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("api/v1/celebrations")
@AllArgsConstructor
public class CelebrationController {

    private final CelebrationService celebrationService;


    @GetMapping
    public List<Celebration> fetchAllCelebrations(){
        return celebrationService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Celebration> getCelebrationById(@PathVariable String id) {
        Celebration celebration = celebrationService.getCelebrationById(id);
        if (celebration != null) {
            return ResponseEntity.ok(celebration);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Celebration> createCelebration(@RequestBody Celebration celebration) {
        Celebration createdCelebration = celebrationService.createCelebration(celebration);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCelebration);
    }

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCelebration(@PathVariable String id) {
        celebrationService.deleteCelebrationById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/eventName/{eventName}")
    public ResponseEntity<Celebration> findCelebrationByEventName(@PathVariable String eventName) {
        Celebration celebration = celebrationService.findCelebrationByEventName(eventName);
        if (celebration != null) {
            return ResponseEntity.ok(celebration);
        }
        return ResponseEntity.notFound().build();
    }
}
