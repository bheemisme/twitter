/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.twitter.controllers;

import com.twitter.models.Comment;
import com.twitter.models.Follower;
import com.twitter.models.Notification;
import com.twitter.models.Tweet;
import com.twitter.models.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
@WebServlet(name = "TweetServlet", urlPatterns = { "/tweet"})
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
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        
        if (email == null) {
            response.sendRedirect("/twitter/login");
        } else {
            logger.log(Level.INFO, email);
            String tweet_id = request.getParameter("tweet_id");;
            logger.log(Level.INFO,tweet_id);
            if (tweet_id != null) {
                try {
                    Tweet twt = Tweet.findTweet(tweet_id);
                    if(twt != null){
                        
                        ArrayList<Comment> comments = Comment.getCommentsByTweet(twt.getId());
                        request.setAttribute("tweet", twt);
                        request.setAttribute("comments", comments);                        
                        logger.log(Level.INFO,twt.getContent());                        
                        request.setAttribute("title", "Tweet");
                        request.getRequestDispatcher("./pages/tweet.jsp").forward(request, response);
                        
                    }else{
                        throw new SQLException(tweet_id + " don't exist");
                    }
                    
                } catch (SQLException | ClassNotFoundException | NamingException ex) {
                    logger.log(Level.SEVERE,ex.getMessage());
                    request.setAttribute("title", "404");
                    request.setAttribute("message", ex.getMessage());
                    request.getRequestDispatcher("./pages/404.jsp").forward(request, response);
                }
            }else{
                request.setAttribute("message", "tweet don't exist");
                request.getRequestDispatcher("./pages/404.jsp").forward(request, response);
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        String method = request.getParameter("_method");
        if (email == null) {
            response.sendRedirect("/twitter/login");
        } else if(method != null && method.equals("post")){

            try {
                
                String content = request.getParameter("tweet");
                Tweet t = Tweet.createTweet(email, content);
                
                for(User user: Follower.getFollowers(email)) {
                    logger.info(user.getEmail());
                    Notification.createNotification(user.getEmail(), t.getId(), "You got a tweet <i>" +  t.getContent().substring(0, 30) + "...</i>");
                }
                if(t == null){
                    throw new SQLException("unable to create the tweet");
                }
                response.sendRedirect("/twitter/home");
            } catch (SQLException | ClassNotFoundException | NamingException ex) {
                logger.log(Level.SEVERE, ex.getLocalizedMessage());
                request.setAttribute("message", ex.getMessage());
                request.getRequestDispatcher("./pages/400.jsp").forward(request, response);
            }

        }else if(method != null && method.equals("delete")) {
            try {    
                String tweet_id = request.getParameter("tweet_id");
                Tweet.deleteTweet(tweet_id);
                response.sendRedirect("/twitter/profile");
            } catch (SQLException | ClassNotFoundException | NamingException ex) {
                logger.log(Level.SEVERE, ex.getLocalizedMessage());
                request.setAttribute("message", ex.getMessage());
                request.getRequestDispatcher("./pages/400.jsp").forward(request, response);
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
