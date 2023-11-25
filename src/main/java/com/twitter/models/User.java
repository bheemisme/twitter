/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.twitter.models;

import com.twitter.db.DBConn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.naming.NamingException;
import com.twitter.utils.ShortUUID;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

/**
 *
 * @author sudarshan
 */
public class User {

    private static final Logger logger = Logger.getLogger("User");
    private String id;
    private String email;
    private String username;
    private String password;
    private String profile_image;

    public User(String email, String username, String profile_image) throws SQLException {
        this.email = email;
        this.username = username;
        this.profile_image = profile_image;
    }

    public User(String id, String email, String username, String password, String profile_image) throws SQLException {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.profile_image = profile_image;

    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getProfileImage() {
        return this.profile_image;
    }

    public String getId() {
        return this.id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProfileImage(String profile_image) {
        this.profile_image = profile_image;
    }

    public static User save(String email, String username, String password) throws SQLException, ClassNotFoundException, NamingException {
        Connection db = DBConn.getConnection();
        logger.info("connection established with DB");
        logger.info(System.getProperty("user.dir"));
        User u = new User(
                ShortUUID.generateShortUUID(),
                email, username, password,
                "./static/images/profile-img.jpg"
        );
        Integer rowCount;
        try (PreparedStatement st = db.prepareStatement("INSERT INTO users (id, email, username, password, profile_image) VALUES (?, ?, ?, ?, ?)")) {

            st.setString(1, u.getId());
            st.setString(2, u.getEmail());
            st.setString(3, u.getUsername());
            st.setString(4, u.getPassword());
            st.setString(5, u.getProfileImage());

            rowCount = st.executeUpdate();
            if (rowCount > 0) {
                return u;
            }
        }

        return null;
    }

    public static User update(String email, String username) throws SQLException, ClassNotFoundException, NamingException {
        Connection db = DBConn.getConnection();
        Integer rowCount;

        try (PreparedStatement st = db.prepareStatement("UPDATE  users  SET username = ? WHERE email = ?")) {
            st.setString(1, username);
            st.setString(2, email);
            rowCount = st.executeUpdate();
        }
        if (rowCount > 0) {
            return User.find(email);
        }
        return null;
    }

    public static User updateImage(String email, String profile_image) throws SQLException, ClassNotFoundException, NamingException {
        Connection db = DBConn.getConnection();
        Integer rowCount;

        try (PreparedStatement st = db.prepareStatement("UPDATE  users  SET profile_image = ? WHERE email = ?")) {
            st.setString(1, profile_image);
            st.setString(2, email);

            rowCount = st.executeUpdate();
        }
        if (rowCount > 0) {
            return User.find(email);
        }
        return null;

    }

    public static User updatePassword(String email, String password, String oldPassword) throws SQLException, ClassNotFoundException, NamingException {
        Connection db = DBConn.getConnection();
        Integer rowCount;

        try (PreparedStatement st = db.prepareStatement("UPDATE  users  SET password = ? WHERE email = ? AND password = ?")) {
            st.setString(1, password);
            st.setString(2, email);
            st.setString(3, oldPassword);

            rowCount = st.executeUpdate();
        }
        if (rowCount > 0) {
            return User.find(email);
        }
        return null;

    }

    public static User find(String email) throws SQLException, ClassNotFoundException, NamingException {
        Connection db = DBConn.getConnection();

        try (PreparedStatement st = db.prepareStatement("SELECT * FROM users WHERE email = ? LIMIT 1")) {
            st.setString(1, email);

            ResultSet user = st.executeQuery();
            if (!user.next()) {
                return null;
            }

            return new User(
                    user.getString("id"),
                    user.getString("email"),
                    user.getString("username"),
                    user.getString("password"),
                    user.getString("profile_image")
            );

        }

    }

    public static ArrayList<User> findAll() throws SQLException, ClassNotFoundException, NamingException {
        Connection db = DBConn.getConnection();
        ArrayList<User> users = new ArrayList<>();
        try (PreparedStatement st = db.prepareStatement("SELECT email, username, profile_image FROM users")) {

            ResultSet results = st.executeQuery();
            while (results.next()) {
                users.add(new User(results.getString("email"), results.getString("username"), results.getString("profile_image")));
            }
        }
        return users;
    }

    public static boolean deleteUser(String email, String contextPath) throws SQLException, ClassNotFoundException, NamingException {
        Connection db = DBConn.getConnection();
        db.setAutoCommit(false);
        try (PreparedStatement st = db.prepareStatement("DELETE FROM users WHERE email = ?")) {
            st.setString(1, email);
            User u = User.find(email);
            if (u == null) {
                throw new SQLException("no user found");
            }
            Path imagePath = Path.of(contextPath + "/" + u.getId());
            if (Files.exists(imagePath)) {
                Files.delete(imagePath);
            }

            Tweet.deleteTweetsByEmail(email);
            Notification.deleteNotificationsByEmail(email);

            if (st.executeUpdate() > 0) {
                db.commit();
                return true;
            }
        }catch(IOException ex){
            logger.info("image file problem");
            logger.info(ex.getMessage());
        } finally {
            db.setAutoCommit(true);
        }
        return false;

    }
}
