package com.example.celebration;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Reprezentuje element na liście zakupów dla wydarzenia Celebration.
 */
@Data
@AllArgsConstructor
public class ShoppingItem {
    private String shoppingItemName;
    private Integer shoppingItemQuantity;
}
