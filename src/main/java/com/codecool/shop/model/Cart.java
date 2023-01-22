package com.codecool.shop.model;

import java.math.BigDecimal;
import java.util.List;

public class Cart {

    private final List<CartItem> items;
    private BigDecimal total = new BigDecimal(0);

    public Cart(List<CartItem> items) {
        this.items = items;
        for (CartItem item : items) {
            this.total = total.add(item.getSumPrice());
        }
    }
}
