/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.twitter.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.twitter.models.User;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author sudarshan
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(LoginServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        logger.info(email);
        logger.info(password);

        try {
            User u = User.find(email);
            if(u.getPassword() == null ? password == null : !u.getPassword().equals(password)){
                request.getRequestDispatcher("./pages/login.jsp").forward(request, response);
            }
            HttpSession session = request.getSession();
            session.setAttribute("email", u.getEmail());
            session.setAttribute("username", u.getUsername());
            response.sendRedirect("/twitter/home");
        } catch (SQLException | ClassNotFoundException | NamingException ex) {
            logger.severe(ex.getLocalizedMessage());
            request.setAttribute("message", ex.getMessage());
            request.getRequestDispatcher("./pages/404.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setAttribute("title", "login");
        request.getRequestDispatcher("./pages/login.jsp").forward(request, response);
    }

}
