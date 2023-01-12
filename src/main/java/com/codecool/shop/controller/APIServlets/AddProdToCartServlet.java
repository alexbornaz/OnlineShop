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
        String prodId = req.getParameter("prodId");
        System.out.println(prodId);
        ProductService pS = ProductService.getInstance();
        Product product = pS.getProductById(Integer.parseInt(prodId));
        CartService cartService = CartService.getInstance();
        System.out.println(cartService.getAll());
        System.out.println(cartService.get(product.getId()) != null);
        if (cartService.get(product.getId()) != null){
            cartService.modify(1,product.getId());
        }else {
            cartService.add(product,1);
        }
        System.out.println(cartService.getAll());
        System.out.println(product.getName()+" added!");
        out.println(new Gson().toJson(product.getName()+" added!"));
        out.flush();
    }
}
