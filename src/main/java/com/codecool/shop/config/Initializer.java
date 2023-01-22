package com.codecool.shop.config;

import com.codecool.shop.controller.APIServlets.CartInfoServlet;
import com.codecool.shop.controller.APIServlets.ProductAPIController;
import com.codecool.shop.controller.Database.DatabaseManager;
import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Objects;


@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DatabaseManager dbManager = DatabaseManager.getInstance();
        ProductDao productDataStore=null;
        ProductCategoryDao productCategoryDataStore=null;
        SupplierDao supplierDataStore=null;

        try {
            dbManager.setup();
        } catch (SQLException | IOException ex) {
            System.out.println("Cannot connect to database.");
        }

        var daoProperty = dbManager.getProperties().getProperty("dao");

        if (Objects.equals(daoProperty, "memory")) {
            productDataStore = ProductDaoMem.getInstance();
            productCategoryDataStore = ProductCategoryDaoMem.getInstance();
            supplierDataStore = SupplierDaoMem.getInstance();

            //setting up a new supplier
            Supplier amazon = new Supplier("Amazon", "Digital content and services");
            supplierDataStore.add(amazon);
            Supplier lenovo = new Supplier("Lenovo", "Computers");
            supplierDataStore.add(lenovo);

            //setting up a new product category
            ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
            ProductCategory laptop = new ProductCategory("Laptop", "Hardware", "Portable personal computer");
            ProductCategory phone = new ProductCategory("SmartPhone", "Hardware", "A smart phone :)");
            productCategoryDataStore.add(tablet);
            productCategoryDataStore.add(laptop);
            productCategoryDataStore.add(phone);
            //setting up products and printing it
            productDataStore.add(new Product("Amazon Fire", new BigDecimal("49.9"), "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
            productDataStore.add(new Product("Lenovo IdeaPad Miix 700", new BigDecimal("479"), "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
            productDataStore.add(new Product("Amazon Fire HD 8", new BigDecimal("89"), "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));

        }else if(Objects.equals(daoProperty, "jdbc")) {
            productDataStore = dbManager.getProductDao();
            productCategoryDataStore = dbManager.getCategoryDao();
            supplierDataStore = dbManager.getSupplierDao();

        }
        ServletContext context = sce.getServletContext();
        context.addServlet("Product", new ProductController(productDataStore, productCategoryDataStore, supplierDataStore)).addMapping("/");
        context.addServlet("CartController", new CartInfoServlet(productDataStore)).addMapping("/api/cart-info");
        context.addServlet("ProductAPI", new ProductAPIController(productDataStore)).addMapping("/api/product");
    }
}
