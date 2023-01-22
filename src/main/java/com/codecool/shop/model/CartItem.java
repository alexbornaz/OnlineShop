package com.codecool.shop.model;

import java.math.BigDecimal;

public class CartItem {

    private int id;
    private String name;
    private String supplier;
    private int quantity;
    private BigDecimal SumPrice;

    public CartItem(int id, String name, String supplier, int quantity, BigDecimal defaultPrice) {
        this.id = id;
        this.name = name;
        this.supplier = supplier;
        this.quantity = quantity;
        SumPrice = defaultPrice.multiply(BigDecimal.valueOf(quantity));
    }

    public BigDecimal getSumPrice() {
        return SumPrice;
    }
}

