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
public class Tweet {
    
    private static final Logger logger = Logger.getLogger(Tweet.class.getName());
    private String id;
    private String email;
    private LocalDateTime date;
    private String content;
    private String user_profile_image;
    
    public Tweet(String id, String email, LocalDateTime date, String content) {
        this.id = id;
        this.email = email;
        this.date = date;
        this.content = content;
    }
    
    public Tweet(String id, String email, LocalDateTime date, String content, String user_profile_image) {
        this.id = id;
        this.email = email;
        this.date = date;
        this.content = content;
        this.user_profile_image = user_profile_image;
    }
    
    public String getId() {
        return this.id;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public LocalDateTime getDate() {
        return this.date;
    }
    
    public String getContent() {
        return this.content;
    }
    
    public String getUserProfileImage(){
        return this.user_profile_image;
    }
    
    public static Tweet createTweet(String email, String content)
            throws SQLException, ClassNotFoundException, NamingException {
        
        Connection db = DBConn.getConnection();
        logger.info("connection established with DB");
        Integer rowCount;
        Tweet t = new Tweet(ShortUUID.generateShortUUID(), email, LocalDateTime.now(), content);
        try (PreparedStatement st = db.prepareStatement("INSERT INTO tweets (id, email, creation_time, content) VALUES (?, ?, ?, ?)")) {
            st.setString(1, t.getId());
            st.setString(2, t.getEmail());
            st.setString(3, t.getDate().toString());
            st.setString(4, t.getContent());
            rowCount = st.executeUpdate();
        }
        if (rowCount > 0) {
            return t;
        }
        return null;
    }
    
    public static ArrayList<Tweet> getTweets(String email) throws SQLException, ClassNotFoundException, NamingException {
        
        Connection db = DBConn.getConnection();
        logger.info("connection established with DB");
        String query = "SELECT * FROM tweets WHERE email = ?";
        ArrayList<Tweet> tweets = new ArrayList<>();
        try (PreparedStatement st = db.prepareStatement(query)) {
            
            st.setString(1, email);
            ResultSet results = st.executeQuery();
            while (results.next()) {
                tweets.add(new Tweet(
                        results.getString(1),
                        results.getString(2),
                        LocalDateTime.parse(results.getString(3)),
                        results.getString(4)
                ));
            }
            return tweets;
        }
        
    }
    
    public static ArrayList<Tweet> getAllTweets() throws SQLException, ClassNotFoundException, NamingException {
        Connection db = DBConn.getConnection();
        logger.info("connection established with DB");
        String query = "SELECT * FROM tweets";
        ArrayList<Tweet> tweets = new ArrayList<>();
        try (PreparedStatement st = db.prepareStatement(query)) {
            
            ResultSet results = st.executeQuery();
            while (results.next()) {
                tweets.add(new Tweet(
                        results.getString("id"),
                        results.getString("email"),
                        LocalDateTime.parse(results.getString("creation_time")),
                        results.getString("content")
                        
                ));
            }
            return tweets;
        }
        
    }
    
    public static ArrayList<Tweet> getAllTweetsWithImages() throws SQLException, ClassNotFoundException, NamingException {
        Connection db = DBConn.getConnection();
        logger.info("connection established with DB");
        String query = "SELECT tweets.id, tweets.email, tweets.creation_time, tweets.content, users.profile_image FROM tweets JOIN users on tweets.email = users.email";
        ArrayList<Tweet> tweets = new ArrayList<>();
        try (PreparedStatement st = db.prepareStatement(query)) {
            
            ResultSet results = st.executeQuery();
            while (results.next()) {
                tweets.add(new Tweet(
                        results.getString("id"),
                        results.getString("email"),
                        LocalDateTime.parse(results.getString("creation_time")),
                        results.getString("content"),
                        results.getString("profile_image")
                ));
            }
            return tweets;
        }
        
    }
    
    public static Tweet findTweet(String tweet_id) throws SQLException, ClassNotFoundException, NamingException {
        Connection db = DBConn.getConnection();
        logger.info("connection established with DB");
        String query = "SELECT tweets.id, tweets.email, tweets.creation_time, tweets.content, users.profile_image FROM tweets JOIN users on tweets.email = users.email WHERE tweets.id=?";
        
        try (PreparedStatement st = db.prepareStatement(query)) {
            st.setString(1, tweet_id);
            ResultSet results = st.executeQuery();
            if (results.next()) {
                return new Tweet(
                        results.getString("id"),
                        results.getString("email"),
                        LocalDateTime.parse(results.getString("creation_time")),
                        results.getString("content"),
                        results.getString("profile_image")
                );
            }
            
        }
        return null;
    }
    
    public static boolean deleteTweet(String tweet_id) throws SQLException, ClassNotFoundException, NamingException {
        Connection db = DBConn.getConnection();
        logger.info("connection established with DB");
        
        Integer rowCount;
        Comment.deleteCommentsByTweet(tweet_id);
        try (PreparedStatement st = db.prepareStatement("DELETE FROM tweets WHERE id = ?")) {
            st.setString(1, tweet_id);
            rowCount = st.executeUpdate();
            if (rowCount > 0) {
                return true;
            }
        }
        
        return false;
    }
    
    public static void deleteTweetsByEmail(String email) throws SQLException, ClassNotFoundException, NamingException {
        Connection db = DBConn.getConnection();
        logger.info("connection established with DB");
        
        Integer commentRowCount, tweetRowCount;
        
        String deleteComments = "DELETE FROM comments WHERE tweet_id IN (SELECT id as tweet_id FROM tweets WHERE email = ?)";
        String deleteTweets = "DELETE FROM tweets WHERE email = ?";
        try (PreparedStatement comments = db.prepareStatement(deleteComments); PreparedStatement tweets = db.prepareStatement(deleteTweets);) {
            comments.setString(1, email);
            tweets.setString(1, email);
            commentRowCount = comments.executeUpdate();
            tweetRowCount = tweets.executeUpdate();
            
        } 
        
        
    }
}
