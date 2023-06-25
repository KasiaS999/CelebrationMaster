package com.example.celebration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CelebrationControllerTest {

    @Mock
    private CelebrationService celebrationService;

    @InjectMocks
    private CelebrationController celebrationController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void fetchAllCelebrations() {
        String dateTimeString1 = "2023-06-25T10:30:00";
        LocalDateTime dateTime1 = LocalDateTime.parse(dateTimeString1);
        String dateTimeString2 = "2023-06-30T10:30:00";
        LocalDateTime dateTime2 = LocalDateTime.parse(dateTimeString2);
        Celebration celebration1 = new Celebration("1", dateTime1);
        Celebration celebration2 = new Celebration("2", dateTime2);
        List<Celebration> celebrations = Arrays.asList(celebration1, celebration2);
        when(celebrationService.getAllCelebrations()).thenReturn(celebrations);
        List<Celebration> result = celebrationController.fetchAllCelebrations();
        assertEquals(celebrations, result);
        verify(celebrationService, times(1)).getAllCelebrations();
    }

    @Test
    void getCelebrationById() {
        String celebrationId = "1";
        String dateTimeString1 = "2023-06-25T10:30:00";
        LocalDateTime dateTime1 = LocalDateTime.parse(dateTimeString1);
        Celebration celebration = new Celebration("1", dateTime1);
        when(celebrationService.getCelebrationById(celebrationId)).thenReturn(celebration);
        ResponseEntity<Celebration> response = celebrationController.getCelebrationById(celebrationId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(celebration, response.getBody());
        verify(celebrationService, times(1)).getCelebrationById(celebrationId);
    }

    @Test
    void createCelebration() {
        String dateTimeString1 = "2023-06-25T10:30:00";
        LocalDateTime dateTime1 = LocalDateTime.parse(dateTimeString1);
        String dateTimeString2 = "2023-06-30T10:30:00";
        LocalDateTime dateTime2 = LocalDateTime.parse(dateTimeString2);
        Celebration celebration = new Celebration("1", dateTime1);
        Celebration createdCelebration = new Celebration("1", dateTime2);

        when(celebrationService.createCelebration(celebration)).thenReturn(createdCelebration);

        ResponseEntity<Celebration> response = celebrationController.createCelebration(celebration);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdCelebration, response.getBody());
        verify(celebrationService, times(1)).createCelebration(celebration);
    }

    @Test
    void updateCelebration() {
        String celebrationId = "1";
        String dateTimeString1 = "2023-06-25T10:30:00";
        LocalDateTime dateTime1 = LocalDateTime.parse(dateTimeString1);
        String dateTimeString2 = "2023-06-30T10:30:00";
        LocalDateTime dateTime2 = LocalDateTime.parse(dateTimeString2);
        Celebration celebrationUpdates = new Celebration("1", dateTime1);

        Celebration updatedCelebration = new Celebration("1", dateTime2);

        when(celebrationService.updateCelebration(celebrationId, celebrationUpdates)).thenReturn(updatedCelebration);

        ResponseEntity<Celebration> response = celebrationController.updateCelebration(celebrationId, celebrationUpdates);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedCelebration, response.getBody());
        verify(celebrationService, times(1)).updateCelebration(celebrationId, celebrationUpdates);
    }

    @Test
    void deleteCelebration() {
        String celebrationId = "1";
        ResponseEntity<Void> response = celebrationController.deleteCelebration(celebrationId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(celebrationService, times(1)).deleteCelebrationById(celebrationId);
    }

    @Test
    void findCelebrationByEventName() {
        String eventName = "Birthday";

        String dateTimeString1 = "2023-06-25T10:30:00";
        LocalDateTime dateTime1 = LocalDateTime.parse(dateTimeString1);
        Celebration celebration = new Celebration(eventName, dateTime1);

        when(celebrationService.findCelebrationByEventName(eventName)).thenReturn(celebration);

        ResponseEntity<Celebration> response = celebrationController.findCelebrationByEventName(eventName);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(celebration, response.getBody());
        verify(celebrationService, times(1)).findCelebrationByEventName(eventName);
    }
}