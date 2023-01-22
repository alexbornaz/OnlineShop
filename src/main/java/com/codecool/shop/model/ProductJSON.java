package com.codecool.shop.model;

public class ProductJSON {

    private final int id;
    private final String name;
    private final String price;
    private final String description;
    private final String supplier;
    private final String category;
    private final int categoryId;
    private final int supplierId;

    public ProductJSON(int id, String name, String price, String description, String supplier, String category, int categoryId, int supplierId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.supplier = supplier;
        this.category = category;
        this.categoryId = categoryId;
        this.supplierId = supplierId;
    }

}
