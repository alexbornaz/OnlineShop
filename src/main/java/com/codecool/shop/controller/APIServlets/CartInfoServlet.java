package com.codecool.shop.controller.APIServlets;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductOnCart;
import com.codecool.shop.service.CartService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = {"/api/cartInfo"})
public class CartInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        List<ProductOnCart> productsOnCart = CartService.getInstance().getAll();
        out.println(new Gson().toJson(productsOnCart));
        out.flush();
    }
}
