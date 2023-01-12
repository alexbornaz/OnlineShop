package com.codecool.shop.controller.APIServlets;

import com.codecool.shop.dao.CartDao;
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

@WebServlet(urlPatterns = {"/api/editProdOnCart"})
public class EditProdOnCart extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductOnCart mockup = new Gson().fromJson(request.getReader(),ProductOnCart.class);
        int prodId = mockup.getId();
        int qty = mockup.getQuantity();
        CartService cartService = CartService.getInstance();
        cartService.modify(qty,prodId);
        Gson gson = new Gson();
        String message = gson.toJson("merge");
        PrintWriter out = response.getWriter();
        out.println(message);
        out.flush();
    }
}
