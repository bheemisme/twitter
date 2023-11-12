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
import java.util.logging.Logger;
import javax.naming.NamingException;
/**
 *
 * @author sudarshan
 */
public class User {
    
    private static final Logger logger =Logger.getLogger("User");
    private String email;
    private String username;
    private String password;
    
    
    public User(String email, String username) throws SQLException {
        this.email = email;
        this.username = username;
    }
    
    
    
    public User(String email, String username, String password) throws SQLException {
        this.email = email;
        this.username = username;
        this.password = password;
        
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getUsername(){
        return username;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public static boolean save(User user) throws SQLException, ClassNotFoundException, NamingException{
        Connection db = DBConn.getConnection(); 
        logger.info("connection established with DB");
        logger.info(System.getProperty("user.dir"));
        boolean success;
        try (PreparedStatement st = db.prepareStatement("INSERT INTO users (email, user_name, password) VALUES (?, ?, ?)")) {
            st.setString(1, user.email);
            st.setString(2, user.username);
            st.setString(3, user.password);
            success = st.execute();
        }
        
        return success;
    }
    
    public static User update(String email, String username) throws SQLException, ClassNotFoundException, NamingException{
        Connection db = DBConn.getConnection();
        boolean success;
        try (PreparedStatement st = db.prepareStatement("UPDATE  users  SET user_name = ? WHERE email = ?")) {
            st.setString(1, username);
            st.setString(2, email);
            success = st.execute();
        }
        if(success){
            return new User(email, username);
        }
        return null;
    }
    
    public static User find(String email) throws SQLException, ClassNotFoundException, NamingException{
        Connection db = DBConn.getConnection();
        String username;
        try (PreparedStatement st = db.prepareStatement("SELECT user_name FROM users WHERE email = ? LIMIT 1")) {
            st.setString(1, email);
            
            ResultSet user = st.executeQuery();
            user.next();
            username = user.getString("user_name");
            
        }
        return new User(email,username);    
    }
}