package com.twitter.controllers;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */


import com.twitter.models.Follower;
import com.twitter.models.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;

/**
 *
 * @author nobal narayanan
 */
@WebServlet(name = "FollowersServlet", urlPatterns = {"/followers"})
public class FollowersServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(FollowersServlet.class.getName());
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
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
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        if(email == null){    
            response.sendRedirect("/twitter/login");
        }else{
            try{
                
                ArrayList<User> followingUsers = Follower.getFollowingUsers(email);
                ArrayList<User> nonFollowingUsers = Follower.getNonFollowingUsers(email);
                ArrayList<User> followers = Follower.getFollowers(email);
                
                request.setAttribute("title", "Followers");
                request.setAttribute("followingUsers", followingUsers);
                request.setAttribute("nonFollowingUsers", nonFollowingUsers);
                request.setAttribute("followers", followers);
                request.getRequestDispatcher("./pages/followers.jsp").forward(request, response);       

            } catch (SQLException | ClassNotFoundException | NamingException ex) {
                logger.severe(ex.getLocalizedMessage());
                request.setAttribute("message", ex.getMessage());
                request.getRequestDispatcher("./pages/404.jsp").forward(request, response);
            }
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
        HttpSession session = request.getSession();
        Object email = session.getAttribute("email");
        String otherEmail = (String) request.getParameter("email");
        String isFollowing = (String) request.getParameter("isFollowing");
        if(email == null){
            response.sendRedirect("/twitter/login");
        }else{
            try{
                logger.info(otherEmail);
                logger.info(isFollowing);
                if("false".equals(isFollowing)){
                    Follower.follow((String) email, otherEmail);
                }else if("true".equals(isFollowing)){
                    Follower.unFollow((String) email, otherEmail);
                }
                
                response.sendRedirect("/twitter/followers");
            } catch (SQLException | ClassNotFoundException | NamingException ex) {
                logger.severe(ex.getLocalizedMessage());
                request.setAttribute("message", ex.getMessage());
                request.getRequestDispatcher("./pages/404.jsp").forward(request, response);
            }
        }
        
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
