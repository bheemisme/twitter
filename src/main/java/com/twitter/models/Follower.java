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
import java.util.HashSet;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author sudarshan
 */
public class Follower {

    private static final Logger logger = Logger.getLogger("Follower");

    public static boolean follow(String email, String toFollowEmail) throws SQLException, ClassNotFoundException, NamingException {
        Connection db = DBConn.getConnection();
        String query = "insert into followers(email, following) values (?, ?)";
        try (PreparedStatement st = db.prepareStatement(query)) {
            st.setString(1, email);
            st.setString(2, toFollowEmail);
            return st.execute();
        }
    }

    public static boolean unFollow(String email, String unFollowEmail) throws SQLException, ClassNotFoundException, NamingException {
        Connection db = DBConn.getConnection();
        String query = "delete from followers where email=? and following=?";
        try (PreparedStatement st = db.prepareStatement(query)) {
            st.setString(1, email);
            st.setString(2, unFollowEmail);
            return st.execute();
        }
    }

    public static ArrayList<User> getFollowers(String email) throws SQLException, ClassNotFoundException, NamingException {
        ArrayList<User> followers = new ArrayList<>();
        Connection db = DBConn.getConnection();
        String query = "select users.email, users.username, users.profile_image from followers join users on followers.email=users.email where followers.following = ?";
        try (PreparedStatement st = db.prepareStatement(query)) {
            st.setString(1, email);
            ResultSet results = st.executeQuery();
            while (results.next()) {
                followers.add(new User(
                        results.getString("email"),
                        results.getString("username"),
                        results.getString("profile_image")
                ));
            }
        }
        return followers;
    }

    public static ArrayList<User> getNonFollowers(String email) throws SQLException, ClassNotFoundException, NamingException {
        ArrayList<User> nonFollowers = User.findAll();
        ArrayList<User> followers = getFollowers(email);

        nonFollowers.removeIf((user) -> {
            if (user.getEmail().equals(email)) {
                return true;
            }
            for (User u : followers) {
                if (user.getEmail().equals(u.getEmail())) {
                    return true;
                }
            }
            return false;
        });
        return nonFollowers;
    }

    public static ArrayList<User> getFollowingUsers(String email) throws SQLException, ClassNotFoundException, NamingException {
        ArrayList<User> followingUsers = new ArrayList<>();
        Connection db = DBConn.getConnection();
        String query
                = "select users.email, users.username, users.profile_image from followers join users on followers.following = users.email where followers.email=?";
        try (PreparedStatement st = db.prepareStatement(query)) {
            st.setString(1, email);

            ResultSet results = st.executeQuery();
            while (results.next()) {
                followingUsers.add(
                        new User(
                                results.getString("email"),
                                results.getString("username"),
                                results.getString("profile_image")));
            }

        }
        return followingUsers;
    }

    public static ArrayList<User> getNonFollowingUsers(String email) throws SQLException, ClassNotFoundException, NamingException {
        ArrayList<User> followingUsers = getFollowingUsers(email);
        ArrayList<User> nonFollowingUsers = User.findAll();

        nonFollowingUsers.removeIf((user) -> {

            if (user.getEmail().equals(email)) {
                return true;
            }

            for (User u : followingUsers) {
                if (user.getEmail().equals(u.getEmail())) {
                    return true;
                }
            }

            return false;
        });

        return nonFollowingUsers;

    }
}
