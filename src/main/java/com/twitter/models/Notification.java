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
public class Notification {

    private static final Logger logger = Logger.getLogger(Notification.class.getName());
    private final String id;
    private final String tweet_id;
    private final String email;
    private final String content;
    private final LocalDateTime creation_time;

    public Notification(String id, String tweet_id, String email, String content, LocalDateTime creation_time) {
        this.id = id;
        this.tweet_id = tweet_id;
        this.email = email;
        this.content = content;
        this.creation_time = creation_time;
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

    public static Notification createNotification(String email, String tweet_id, String content) throws SQLException, ClassNotFoundException, NamingException {
        Connection db = DBConn.getConnection();
        logger.info("connection established with DB");
        Integer rowCount;
        Notification n = new Notification(ShortUUID.generateShortUUID(),
                tweet_id,
                email,
                content,
                LocalDateTime.now()
        );
        try (PreparedStatement st = db.prepareStatement("INSERT INTO comments (id, content, email, tweet_id, creation_time) VALUES (?, ?, ?, ?, ?)")) {
            st.setString(1, n.getId());
            st.setString(2, n.getContent());
            st.setString(3, n.getEmail());
            st.setString(4, n.getTweetId());
            st.setString(5, n.getCreationTime().toString());

            rowCount = st.executeUpdate();
            if (rowCount > 0) {
                return n;
            }

        }
        return null;

    }

    public static ArrayList<Notification> getNotificationsByEmail(String email) throws SQLException, ClassNotFoundException, NamingException {
        Connection db = DBConn.getConnection();
        logger.info("connection established with DB");
        ArrayList<Notification> notifications = new ArrayList<>();

        try (PreparedStatement st = db.prepareStatement("SELECT * FROM notifications WHERE email = ?")) {
            st.setString(1, email);

            ResultSet results = st.executeQuery();
            while (results.next()) {
                notifications.add(new Notification(
                        results.getString("id"),
                        results.getString("content"),
                        results.getString("email"),
                        results.getString("tweet_id"),
                        LocalDateTime.parse(results.getString("creation_time"))
                ));
            }

        }
        return notifications;
    }

    public static ArrayList<Notification> getNotificationsByTweet(String tweet_id) throws SQLException, ClassNotFoundException, NamingException {
        Connection db = DBConn.getConnection();
        logger.info("connection established with DB");
        ArrayList<Notification> notifications = new ArrayList<>();

        try (PreparedStatement st = db.prepareStatement("SELECT * FROM notifications WHERE tweet_id = ?")) {
            st.setString(1, tweet_id);

            ResultSet results = st.executeQuery();
            while (results.next()) {
                notifications.add(new Notification(
                        results.getString("id"),
                        results.getString("content"),
                        results.getString("email"),
                        results.getString("tweet_id"),
                        LocalDateTime.parse(results.getString("creation_time"))
                ));
            }

        }
        return notifications;
    }

    public static Notification getNotificationsById(String id) throws SQLException, ClassNotFoundException, NamingException {
        Connection db = DBConn.getConnection();
        logger.info("connection established with DB");
        Notification n = null;
        try (PreparedStatement st = db.prepareStatement("SELECT * FROM notifications WHERE id = ?")) {
            st.setString(1, id);

            ResultSet results = st.executeQuery();
            if (results.next()) {
                n = new Notification(
                        results.getString("id"),
                        results.getString("content"),
                        results.getString("email"),
                        results.getString("tweet_id"),
                        LocalDateTime.parse(results.getString("creation_time"))
                );

            }

        }
        return n;
    }

    public static void deleteNotificationsByEmail(String email) throws SQLException, ClassNotFoundException, NamingException {
        Connection db = DBConn.getConnection();
        logger.info("connection established with DB");
        try (PreparedStatement st = db.prepareStatement("DELETE FROM notifications WHERE email = ?")) {
            st.setString(1, email);
            st.executeUpdate();
        }
    }
}
