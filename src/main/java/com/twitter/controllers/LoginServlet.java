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
import com.twitter.utils.PasswordHashing;
import jakarta.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
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
            String hashedPassword = PasswordHashing.hashPassword(password);
            logger.info(hashedPassword);
            User u = User.find(email);
            if(u == null || password == null || u.getPassword() == null || !u.getPassword().equals(hashedPassword)){
                request.getRequestDispatcher("./pages/login.jsp").forward(request, response);
            }else{
                HttpSession session = request.getSession();
                session.setAttribute("email", u.getEmail());
                session.setAttribute("username", u.getUsername());
                session.setAttribute("profile_image", u.getProfileImage());
                response.sendRedirect("/twitter/home");
            }
            
        } catch (SQLException | ClassNotFoundException | NamingException  ex) {
            logger.severe(ex.getLocalizedMessage());
            request.setAttribute("message", ex.getMessage());
            request.getRequestDispatcher("./pages/404.jsp").forward(request, response);
        } catch (NoSuchAlgorithmException ex){
            request.setAttribute("message", ex.getMessage());
            request.getRequestDispatcher("./pages/401.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String existingUser = (String) request.getSession().getAttribute("email");
        if(existingUser != null){
            response.sendRedirect("/twitter/home");
            return;
        }
        request.setAttribute("title", "login");
        request.getRequestDispatcher("./pages/login.jsp").forward(request, response);
    }

}
