package com.example.celebration;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Reprezentuje element menu dla wydarzenia Celebration.
 */
@Data
@AllArgsConstructor
public class MenuItem {
    private String menuItemName;
    private String menuItemDescription;

    /**
     * Tworzy pusty element menu.
     */
    public MenuItem() {
        new MenuItem("");
    }

    /**
     * Tworzy element menu o podanej nazwie.
     *
     * @param menuItemName nazwa elementu menu
     */
    public MenuItem(String menuItemName) {
        this.menuItemName = menuItemName;
    }
}
