package com.codecool.shop.controller.APIServlets;

import com.codecool.shop.model.Product;
import com.codecool.shop.service.CartService;
import com.codecool.shop.service.ProductService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/api/addToCart"})
public class AddProdToCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        int prodId = Integer.parseInt(req.getParameter("prodId"));
        Product product = ProductService.getInstance().getProductById(prodId);
        CartService cartService = CartService.getInstance();

        if (cartService.get(product.getId()) != null){
            cartService.modify(1,prodId);
        }else {
            cartService.add(product,1);
        }
        System.out.println(product.getName()+" added!");
        out.println(new Gson().toJson(product.getName()+" added!"));
        out.flush();
    }
}
