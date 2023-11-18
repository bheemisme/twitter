/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.twitter.models;

import com.twitter.db.DBConn;
import com.twitter.utils.ShortUUID;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
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

    public Tweet(String id, String email, LocalDateTime date, String content) {
        this.id = id;
        this.email = email;
        this.date = date;
        this.content = content;
    }
    
    public String getId(){
        return this.id;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    
    public LocalDateTime getDate(){
        return this.date;
    }
    
    public String getContent(){
        return this.email;
    }
    
    public static Tweet createTweet(String email, String content) throws SQLException, ClassNotFoundException, NamingException{
        User user = User.find(email);
        Connection db = DBConn.getConnection(); 
        logger.info("connection established with DB");
        boolean success;
        Tweet t= new Tweet(ShortUUID.generateShortUUID(), user.getEmail(), LocalDateTime.now(),content);
        try (PreparedStatement st = db.prepareStatement("INSERT INTO tweets (id, email, creation_time, content) VALUES (?, ?, ?)")) {
            st.setString(1, t.getId());
            st.setString(2, t.getEmail());
            st.setString(3, t.getDate().toString());
            st.setString(4, t.getContent());
            success = st.execute();
        }
        if(success){
            return t;
        }
        return null;
    }
}
