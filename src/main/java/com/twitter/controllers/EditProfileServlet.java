/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.twitter.controllers;

import com.twitter.controllers.HomeServlet;
import com.twitter.models.Tweet;
import com.twitter.models.User;
import com.twitter.utils.ShortUUID;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author sudarshan
 */
@WebServlet(name = "EditProfileServlet", urlPatterns = {"/edit"})
@MultipartConfig
public class EditProfileServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(HomeServlet.class.getName());

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
        if (email == null) {
            response.sendRedirect("/twitter/login");
        } else {
            request.setAttribute("title", "Edit Profile");
            request.getRequestDispatcher("./pages/edit-profile.jsp").forward(request, response);

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
        String email = (String) session.getAttribute("email");
        String method = request.getParameter("method");

        if (email == null) {
            response.sendRedirect("/twitter/login");
        } else if (method != null && method.equals("username")) {
            try {

                String newUsername = request.getParameter("username");
                User u = User.update(email, newUsername);

                session.setAttribute("email", u.getEmail());
                session.setAttribute("username", u.getUsername());
                request.setAttribute("title", "Edit Profile");
                request.getRequestDispatcher("./pages/edit-profile.jsp").forward(request, response);

            } catch (SQLException | ClassNotFoundException | NamingException ex) {
                logger.severe(ex.getLocalizedMessage());
                request.setAttribute("message", ex.getMessage());
                request.getRequestDispatcher("./pages/404.jsp").forward(request, response);
            }
        } else if (method != null && method.equals("image")) {
            try {
                User u = User.find(email);
                Part filePart = request.getPart("image");
                InputStream imageFile = filePart.getInputStream();
                logger.info(email);

                String uploadDirectory = request.getServletContext().getRealPath("static/images");

                String fileName = u.getId();

                Path filePath = Path.of(uploadDirectory, fileName);

                if (Files.exists(filePath)) {
                    Files.copy(imageFile, filePath, StandardCopyOption.REPLACE_EXISTING);
                } else {
                    Files.copy(imageFile, filePath);
                }

                logger.log(Level.INFO, "image: {0}", filePath.toString());

                u = User.updateImage(email, "./static/images/" + fileName);

                session.setAttribute("profile_image", u.getProfileImage());
                request.setAttribute("title", "Edit Profile");
                request.getRequestDispatcher("./pages/edit-profile.jsp").forward(request, response);
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
