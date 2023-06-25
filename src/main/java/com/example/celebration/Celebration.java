package com.example.celebration;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDateTime;
import java.util.List;

/**
 * Klasa reprezentująca wydarzenie Celebration.
 */
@Data
@Document
public class Celebration {
    @Id
    private String id;
    @Indexed(unique=true)
    private String eventName;
    private LocalDateTime eventDateTime;
    private String notes;
    private List<MenuItem> menuItems;
    private List<ShoppingItem> shoppingItems;
    private List<String> guests;
    private String eventPlan;

    /**
     * Konstruktor domyślny klasy Celebration.
     * Tworzy nową instancję Celebration z domyślnymi wartościami.
     */
    public Celebration() {
        new Celebration("eventName", LocalDateTime.now());
    }

    /**
     * Konstruktor klasy Celebration.
     *
     * @param eventName      nazwa wydarzenia
     * @param eventDateTime  data i godzina wydarzenia
     */
    public Celebration(String eventName, LocalDateTime eventDateTime) {
        this.eventName = eventName;
        this.eventDateTime = eventDateTime;
    }

    /**
     * Konstruktor klasy Celebration.
     *
     * @param eventName       nazwa wydarzenia
     * @param eventDateTime   data i godzina wydarzenia
     * @param notes           notatki dotyczące wydarzenia
     * @param menuItems       lista elementów menu
     * @param shoppingItems   lista elementów na liście zakupów
     * @param guests          lista gości
     * @param eventPlan       plan wydarzenia
     */
    public Celebration(String eventName,
                       LocalDateTime eventDateTime,
                       String notes, List<MenuItem> menuItems,
                       List<ShoppingItem> shoppingItems,
                       List<String> guests,
                       String eventPlan) {
        this.eventName = eventName;
        this.eventDateTime = eventDateTime;
        this.notes = notes;
        this.menuItems = menuItems;
        this.shoppingItems = shoppingItems;
        this.guests = guests;
        this.eventPlan = eventPlan;
    }

}
