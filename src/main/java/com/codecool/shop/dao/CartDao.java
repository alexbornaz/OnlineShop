package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductOnCart;

import java.util.List;

public interface CartDao {
    void add(Product prod,int quantity);
    void modify(int prodId,int amount);
    ProductOnCart get(int prodId);
    List<ProductOnCart> getAll();
}
