/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.twitter.controllers;

import com.twitter.models.Tweet;
import com.twitter.models.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author ram shankar
 */
@WebServlet(name = "ProfileServlet", urlPatterns = {"/profile"})
public class ProfileServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(HomeServlet.class.getName());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("email");
            if (email == null) {
                response.sendRedirect("/twitter/login");
            } else {
                String user_email = request.getParameter("user_email");
                if (user_email == null) {
                    User u = User.find(email);
                    ArrayList<Tweet> tweets = Tweet.getTweets(email);
                    
                    request.setAttribute("user", u);
                    request.setAttribute("title", "Profile");
                    request.setAttribute("tweets", tweets);
                    request.getRequestDispatcher("./pages/profile.jsp").forward(request, response);
                }else{
                    User u = User.find(user_email);
                    ArrayList<Tweet> tweets = Tweet.getTweets(user_email);
                    
                    request.setAttribute("user", u);
                    request.setAttribute("title", "Profile");
                    request.setAttribute("tweets", tweets);
                    request.getRequestDispatcher("./pages/profile.jsp").forward(request, response);
                }

            }
        } catch (SQLException | ClassNotFoundException | NamingException ex) {
            logger.severe(ex.getLocalizedMessage());
            request.setAttribute("message", ex.getMessage());
            request.getRequestDispatcher("./pages/404.jsp").forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
