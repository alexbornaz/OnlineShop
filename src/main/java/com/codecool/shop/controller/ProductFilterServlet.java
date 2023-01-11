package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.service.ProductService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/api/filtered"})
public class ProductFilterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        int supplierId = Integer.parseInt(req.getParameter("supplierId"));
        System.out.println(categoryId+ "  "+ supplierId);
        List<Product> filteredProducts = ProductDaoMem.getInstance().getAll().stream()
                .filter(product -> (product.getProductCategoryId() == categoryId || categoryId == 0) &&
                        (product.getSupplierId() == supplierId || supplierId == 0)).collect(Collectors.toList());
        out.println(new Gson().toJson(filteredProducts));
        out.flush();
    }
}
