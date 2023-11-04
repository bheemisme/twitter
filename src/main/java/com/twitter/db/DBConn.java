/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.twitter.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author sudarshan
 */
public class DBConn {

    private final static String URI = "jdbc:sqlite:twitter.db";
    private static Connection conn;
    public DBConn() {
    }
    
    public static Connection getConnection() throws SQLException, ClassNotFoundException{
        if(conn == null){
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(URI);
        }
        return conn;
    }
    
}
