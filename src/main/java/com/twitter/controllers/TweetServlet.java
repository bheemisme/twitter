/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.twitter.controllers;


import com.twitter.models.Tweet;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author sudarshan
 */
@WebServlet(name = "TweetServlet", urlPatterns = {"/tweet"})
public class TweetServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(TweetServlet.class.getName());

    

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
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object email = session.getAttribute("email");
        if(email == null)
        {
            response.sendRedirect("/twitter/login");
        }else{
            try {
                logger.log(Level.INFO, email.toString());
                Tweet t = Tweet.createTweet((String) email, request.getParameter("tweet"));
                response.sendRedirect("/twitter/profile");
            } catch (SQLException | ClassNotFoundException | NamingException ex) {
                logger.severe(ex.getLocalizedMessage());
                request.setAttribute("message", ex.getMessage());
                request.getRequestDispatcher("./pages/404.jsp").forward(request, response);
            }   
            
        }
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
