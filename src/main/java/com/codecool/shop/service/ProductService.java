package com.codecool.shop.service;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.List;
import java.util.stream.Collectors;

public class ProductService {
    private ProductDao productDao;
    private ProductCategoryDao productCategoryDao;
    private SupplierDao supplierDao;

    private static ProductService instance = null;


    private ProductService(ProductDao productDao, ProductCategoryDao productCategoryDao, SupplierDao supplierDao) {
        this.productDao = productDao;
        this.productCategoryDao = productCategoryDao;
        this.supplierDao = supplierDao;
    }

    public static ProductService createInstance(ProductDao productDao, ProductCategoryDao productCategoryDao, SupplierDao supplierDao){
        instance = new ProductService(productDao,productCategoryDao,supplierDao);
        return instance;
    }
    public static ProductService getInstance() {
        if (instance == null){
            throw new Error("ProductService Object not created yet!");
        }
        return instance;
    }




    public ProductCategory getProductCategory(int categoryId) {
        return productCategoryDao.find(categoryId);
    }

    public List<ProductCategory> getAllCategories() {
        return productCategoryDao.getAll();
    }

    public List<Product> getProductsForCategory(int categoryId) {
        var category = productCategoryDao.find(categoryId);
        return productDao.getBy(category);
    }

    public List<Supplier> getAllSuppliers(){
        return supplierDao.getAll();
    }
    public List<Product> getAllProducts(){
        return productDao.getAll();
    }
    public Product getProductById(int id){
        return productDao.find(id);
    }
}


