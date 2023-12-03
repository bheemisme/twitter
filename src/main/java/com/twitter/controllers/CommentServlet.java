/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.twitter.controllers;

import com.twitter.models.Tweet;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpSession;
import com.twitter.models.Comment;
import com.twitter.models.Notification;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author sudarshan
 */
@WebServlet(name = "CommentServlet", urlPatterns = {"/comment"})
public class CommentServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(CommentServlet.class.getName());

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CommentServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CommentServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        processRequest(request, response);
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
        String email = (String) session.getAttribute("email");
        String method = request.getParameter("_method");
        if (email == null) {
            response.sendRedirect("/twitter/login");
        } else if (method != null && method.equals("post")) {
            try {
                String content = request.getParameter("comment");
                String tweet_id = request.getParameter("tweet_id");
                Tweet t = Tweet.findTweet(tweet_id);
                Comment c = Comment.createComment(content, tweet_id, email);
                if (c == null) {
                    logger.log(Level.INFO, "comment is null");
                } else {
                    logger.log(Level.INFO, c.toString());
                }
                if(!t.getEmail().equals(email)){
                    Notification.createNotification(t.getEmail(), tweet_id, "You got a comment <i>"+content.substring(0, 10)+"</i>");
                }
                

                response.sendRedirect("/twitter/tweet?tweet_id=" + tweet_id);

            } catch (SQLException | ClassNotFoundException | NamingException ex) {
                logger.log(Level.SEVERE, ex.getLocalizedMessage());
                request.setAttribute("message", ex.getMessage());
                request.getRequestDispatcher("./pages/400.jsp").forward(request, response);
            }

        } else if (method != null && method.equals("delete")) {
            try {
                String comment_id = request.getParameter("comment_id");
                String tweet_id = request.getParameter("tweet_id");
                
                Comment.deleteComment(comment_id);
                response.sendRedirect("/twitter/tweet?tweet_id=" + tweet_id);
            
            } catch (SQLException | ClassNotFoundException | NamingException ex) {
                logger.log(Level.SEVERE, ex.getLocalizedMessage());
                request.setAttribute("message", ex.getMessage());
                request.getRequestDispatcher("./pages/400.jsp").forward(request, response);
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
