package com.codecool.shop.model;

public class ProductOnCart extends Product{
    private int quantity;

    public ProductOnCart(Product product, int quantity) {
        super(product.name, product.getDefaultPrice(), product.getDefaultCurrency().toString(), product.description,
                product.getProductCategory(), product.getSupplier());
        this.quantity=quantity;
        this.id = product.getId();
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
