/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.twitter.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.naming.NamingException;

import com.twitter.utils.PasswordHashing;

import com.twitter.models.User;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author sudarshan
 */
@WebServlet(name = "SignupServlet", urlPatterns = {"/signup"})
public class SignupServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger("SignupServlet");

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        logger.info(email);
        logger.info(username);
        logger.info(password);
        
        try {
            String hashedPassword = PasswordHashing.hashPassword(password);
            User u = User.save(email, username,hashedPassword);
            if(u != null){
                HttpSession session = request.getSession();
                session.setAttribute("email", u.getEmail());
                session.setAttribute("username", u.getUsername());
                session.setAttribute("profile_image", u.getProfileImage());
            }
            
            response.sendRedirect("/twitter/home");
        } catch (SQLException | ClassNotFoundException | NamingException  ex) {
            logger.severe(ex.getLocalizedMessage());
            request.setAttribute("message", ex.getMessage());
            request.getRequestDispatcher("./pages/404.jsp").forward(request, response);
        } catch(NoSuchAlgorithmException ex) {
            request.setAttribute("message", ex.getMessage());
            request.getRequestDispatcher("./pages/401.jsp").forward(request, response);
        }
        
        
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String existingUser = (String) request.getSession().getAttribute("email");
        if(existingUser != null){
            response.sendRedirect("/twitter/home");
            return;
        }
        
        request.setAttribute("title", "signup");
        request.getRequestDispatcher("./pages/signup.jsp").forward(request, response);
    }
}
