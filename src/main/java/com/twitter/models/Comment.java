/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.twitter.models;

import com.twitter.db.DBConn;
import com.twitter.utils.ShortUUID;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author sudarshan
 */
public class Comment {

    private static final Logger logger = Logger.getLogger(Comment.class.getName());
    private String id;
    private String email;
    private String content;
    private String tweet_id;
    private LocalDateTime creation_time;
    private String user_profile_image;

    public Comment(String id, String email, String content, String tweet_id, LocalDateTime creation_time) {
        this.id = id;
        this.email = email;
        this.content = content;
        this.tweet_id = tweet_id;
        this.creation_time = creation_time;
    }

    public Comment(String id, String email, String content, String tweet_id, LocalDateTime creation_time, String user_profile_image) {
        this.id = id;
        this.email = email;
        this.content = content;
        this.tweet_id = tweet_id;
        this.creation_time = creation_time;
        this.user_profile_image = user_profile_image;
    }

    public String getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public String getContent() {
        return this.content;
    }

    public String getTweetId() {
        return this.tweet_id;
    }

    public LocalDateTime getCreationTime() {
        return this.creation_time;
    }

    public String getProfileImage() {
        return this.user_profile_image;
    }

    public static Comment createComment(String content, String tweet_id, String email)
            throws SQLException, ClassNotFoundException, NamingException {

        Connection db = DBConn.getConnection();
        logger.info("connection established with DB");
        Integer rowCount;
        Comment c = new Comment(ShortUUID.generateShortUUID(), email, content, tweet_id, LocalDateTime.now());
        try (PreparedStatement st = db.prepareStatement("INSERT INTO comments (id, content, email, tweet_id, creation_time) VALUES (?, ?, ?, ?, ?)")) {

            st.setString(1, c.getId());
            st.setString(2, c.getContent());
            st.setString(3, c.getEmail());
            st.setString(4, c.getTweetId());
            st.setString(5, c.getCreationTime().toString());

            rowCount = st.executeUpdate();
            if (rowCount > 0) {
                return c;
            }
        }

        return null;
    }

    public static ArrayList<Comment> getCommentsByTweet(String tweet_id) throws SQLException, ClassNotFoundException, NamingException {
        Connection db = DBConn.getConnection();
        logger.info("connection established with DB");
        ArrayList<Comment> comments = new ArrayList<>();
        try (PreparedStatement st = db.prepareStatement("SELECT comments.id, comments.email, comments.content, comments.tweet_id, comments.creation_time,users.profile_image FROM comments JOIN users ON users.email = comments.email  WHERE tweet_id = ?")) {
            st.setString(1, tweet_id);
            ResultSet results = st.executeQuery();

            while (results.next()) {
                comments.add(new Comment(
                        results.getString("id"),
                        results.getString("email"),
                        results.getString("content"),
                        results.getString("tweet_id"),
                        LocalDateTime.parse(results.getString("creation_time")),
                        results.getString("profile_image")
                ));

            }
        }
        return comments;
    }

    public static Comment getCommentById(String comment_id) throws SQLException, ClassNotFoundException, NamingException {
        Connection db = DBConn.getConnection();
        logger.info("connection established with DB");
        Comment c = null;
        try (PreparedStatement st = db.prepareStatement("SELECT comments.id, comments.email, comments.content, comments.tweet_id, comments.creation_time,users.profile_image FROM comments JOIN users ON users.email = comments.email WHERE id = ?")) {
            st.setString(1, comment_id);
            ResultSet results = st.executeQuery();

            if (results.next()) {
                c = new Comment(
                        results.getString("id"),
                        results.getString("email"),
                        results.getString("content"),
                        results.getString("tweet_id"),
                        LocalDateTime.parse(results.getString("creation_time")),
                        results.getString("profile_image")
                );
            }
        }
        return c;
    }

    public static boolean deleteComment(String comment_id) throws SQLException, ClassNotFoundException, NamingException {
        Connection db = DBConn.getConnection();
        logger.info("connection established with DB");
        Integer rowCount;
        try (PreparedStatement st = db.prepareStatement("DELETE FROM comments WHERE id = ?")) {
            st.setString(1, comment_id);
            rowCount = st.executeUpdate();

            if (rowCount > 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean deleteCommentsByTweet(String tweet_id) throws SQLException, ClassNotFoundException, NamingException {
        Connection db = DBConn.getConnection();
        logger.info("connection established with DB");
        Integer rowCount;
        try (PreparedStatement st = db.prepareStatement("DELETE FROM comments WHERE tweet_id = ?")) {
            st.setString(1, tweet_id);
            rowCount = st.executeUpdate();

            if (rowCount > 0) {
                return true;
            }
        }
        return false;
    }

}
