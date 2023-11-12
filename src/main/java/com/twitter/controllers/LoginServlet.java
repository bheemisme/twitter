/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.twitter.controllers;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.twitter.db.DBConn;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author sudarshan
 */
@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
//        try {
//            
//            Connection conn = DBConn.getConnection();
//            request.setAttribute("msg", "connection established");
//        } catch (SQLException | ClassNotFoundException | NamingException e) {
//            request.setAttribute("msg", e.getMessage());
//        } finally {
//            dispatcher.forward(request, response);
//        }
//
//    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("email");
        String password = request.getParameter("password");
        Logger.getLogger("LoginServlet").log(Level.INFO, username);
        // Authenticate the user.
        if (username.equals("trung") && password.equals("1111")) {
            // Authentication successful.
            response.sendRedirect("/pages/home.html");
        } else {
            // Authentication failed.
            response.sendRedirect("/pages/404.html");
        }
    }


}
