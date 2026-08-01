package com.example; //defines the project package

import jakarta.servlet.*; // servlet fuctionality

import jakarta.servlet.http.*;// ,,     ,,

import java.io.IOException;

import java.util.*;//for list and the Arraylist

public class CartServlet extends HttpServlet { // it handels Http request (get and post)

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)// its uesd for when user submit a from

            throws ServletException, IOException {

        String item = request.getParameter("item");// its help to read itam namne from input

        HttpSession session = request.getSession();// store the data per user

        List<String> cart = (List<String>) session.getAttribute("cart");// help to retriv the cart from session / here the type casting is requerd, object --> string

        if (cart == null) {// if there a fist time user the cart dos not exist

            cart = new ArrayList<>(); 

        }

        if (item != null && !item.trim().isEmpty()) { // its help to avoid empty or null itams

            cart.add(item);

        }

        session.setAttribute("cart", cart);// help to update the session data
        response.sendRedirect("cart");// redriect to cart.jsp / triger doget method

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)//its called when user visit

            throws ServletException, IOException {

        HttpSession session = request.getSession();

        List<String> cart = (List<String>) session.getAttribute("cart");// it passes the cart data to jsp page

        request.setAttribute("cartItems", cart);

        RequestDispatcher rd = request.getRequestDispatcher("/cart.jsp");// help  to send request to cart.jsp and jsp will display the crat itam

        rd.forward(request, response);

    }

}
// fist post/cart --> web.xml  map the servlet--> dopost rederred /cart --> doget forword /run cart.jsp