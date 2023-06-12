package com.example.celebration;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MenuItem {
    private String menuItemName;
    private String menuItemDescription;

    public MenuItem() {
        new MenuItem("");
    }
    public MenuItem(String menuItemName) {
        this.menuItemName = menuItemName;
    }
}
