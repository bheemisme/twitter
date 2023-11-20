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

/**
 *
 * @author sudarshan
 */
public class Follower {
    private static final Logger logger =Logger.getLogger("Follower");
    
    public static boolean follow(String email, String toFollowEmail) throws SQLException, ClassNotFoundException, NamingException {
        Connection db = DBConn.getConnection();
        String query = "insert into followers(email, follower) values (?, ?)";
        try (PreparedStatement st = db.prepareStatement(query)) {
            st.setString(1, email);
            st.setString(2, toFollowEmail);
            return st.execute();
        }   
    }
    
    public static boolean unFollow(String email, String unFollowEmail) throws SQLException, ClassNotFoundException, NamingException {
        Connection db = DBConn.getConnection();
        String query = "delete from followers where email=? and follower=?";
        try (PreparedStatement st = db.prepareStatement(query)) {
            st.setString(1, email);
            st.setString(2, unFollowEmail);
            return st.execute();
        }   
    }
    
    
    public static ArrayList<User> getFollowers(String email) throws SQLException, ClassNotFoundException, NamingException{
        ArrayList<User> followers = new ArrayList<>();
        Connection db = DBConn.getConnection();
        String query = "select users.email, users.user_name from followers join users on followers.follower=users.email where followers.email = ?";
        try (PreparedStatement st = db.prepareStatement(query)) {
            st.setString(1, email);
            ResultSet results = st.executeQuery();
            while(results.next()){
                followers.add(new User(results.getString(1), results.getString(2)));
            }
        }
        return followers;
    }
    
    public static ArrayList<User> getNonFollowers(String email) throws SQLException, ClassNotFoundException, NamingException{
        ArrayList<User> nonFollowers = new ArrayList<>();
        
        Connection db = DBConn.getConnection();
        String query = 
                "select users.email, users.user_name from followers join users on followers.follower=users.email where followers.email!=? and users.email!=?";
        try (PreparedStatement st = db.prepareStatement(query)) {
            st.setString(1, email);
            st.setString(2, email);
            ResultSet results = st.executeQuery();
            while(results.next()){
                nonFollowers.add(new User(results.getString(1), results.getString(2)));
            }
        }
        return nonFollowers;
    }
}
