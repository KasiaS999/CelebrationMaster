package com.example.celebration;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShoppingItem {
    private String shoppingItemName;
    private Integer shoppingItemQuantity;
}
