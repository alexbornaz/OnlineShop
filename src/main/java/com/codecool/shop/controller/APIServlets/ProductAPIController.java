package com.codecool.shop.controller.APIServlets;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductJSON;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

public class ProductAPIController extends HttpServlet {

    private final ProductDao productDataStore;

    public ProductAPIController(ProductDao productDataStore) {
        this.productDataStore = productDataStore;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        List<Product> products = productDataStore.getAll();

        List<ProductJSON> productJSONS = products.stream().map(product -> new ProductJSON(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getSupplier().getName(),
                product.getProductCategory().getName(),
                product.getProductCategory().getId(),
                product.getSupplier().getId())).collect(Collectors.toList());

        Gson gson = new Gson();

        PrintWriter out = response.getWriter();
        out.println(gson.toJson(productJSONS));
    }
}
