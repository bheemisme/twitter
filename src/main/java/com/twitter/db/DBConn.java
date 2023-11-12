/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.twitter.db;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import jakarta.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


/**
 *
 * @author sudarshan
 */
public class DBConn {

    @Resource(name = "jdbc/TweeterDB")
    private static DataSource ds;

    private final static String URI = "jdbc:sqlite:twitter.db";
    private static Connection conn;

    public DBConn() {
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException, NamingException {

        if (conn == null) {
            if (ds == null) {
                Context envCtx = (Context) InitialContext.doLookup("java:comp/env");
                ds = (DataSource) envCtx.lookup("jdbc/TwitterDB");
            }

            conn = ds.getConnection();
        }
        return conn;
    }

}
