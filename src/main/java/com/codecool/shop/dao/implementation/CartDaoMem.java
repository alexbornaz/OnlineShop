package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductOnCart;

import java.util.ArrayList;
import java.util.List;

public class CartDaoMem implements CartDao {
    private List<ProductOnCart> data = new ArrayList<>();
    private static CartDaoMem instance = null;
    private CartDaoMem(){

    }
    public static CartDaoMem getInstance(){
        if (instance==null){
            instance = new CartDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Product prod,int quantity) {
        data.add(new ProductOnCart(prod,quantity));
    }

    @Override
    public void modify(int amount, int prodId) {
        ProductOnCart product = get(prodId);
        System.out.println(product.getId());
        if (product.getQuantity() + amount < 1) {
            data.remove(product);
        } else {
            product.setQuantity(product.getQuantity() + amount);
        }
    }

    @Override
    public ProductOnCart get(int prodId) {
        return data.stream().filter(productOnCart -> productOnCart.getId() == prodId).findFirst().orElse(null);
    }

    @Override
    public List<ProductOnCart> getAll() {
        return data;
    }
}
