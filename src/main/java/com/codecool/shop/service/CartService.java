package com.codecool.shop.service;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductOnCart;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class CartService {
    private CartDao cartDao;

    private static CartService instance = null;

    private CartService(CartDao cartDao) {
        this.cartDao = cartDao;
    }
    public static CartService createInstance(CartDao cartDao){
        instance = new CartService(cartDao);
        return instance;
    }
    public static CartService getInstance(){
        if (instance == null){
            throw new Error("Instance not created yet!");
        }
        return instance;
    }


    public void add(Product product, int amount){
        cartDao.add(product, amount);
    }

    public void modify(int amount, int productId){
        cartDao.modify(amount, productId);
    }

    public ProductOnCart get(int productId){
        return cartDao.get(productId);
    }


    public List<ProductOnCart> getAll(){
        return cartDao.getAll();
    }
    public BigDecimal getTotalPrice(){
        BigDecimal total = getAll().stream().map((productOnCart) -> productOnCart.total()).reduce((x, y) -> x.add(y)).get();
        return total;
    }
}
