package com.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOConnectionManager {

    public static Connection getConnection() throws SQLException{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/teststockeasy", "root", "Rootoorn");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
