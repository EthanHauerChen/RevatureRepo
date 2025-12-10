package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BasicConnection {

    // JDBC URL Format: jdbc:postgresql://host:port/database

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    //should not hardcode password, should save elswhere like env variable or something
    private static final String PASSWORD = "postgres";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        //try with resources since connection is a stream that must be closed
        try(Connection conn = getConnection()){ //DriverManager.getConnection(...)

            System.out.println("Connected to Postgresql!");
            System.out.println("Database: " + conn.getMetaData().getDatabaseProductName());
            System.out.println("Version: " + conn.getMetaData().getDatabaseProductVersion());

        }catch(SQLException e){
            System.err.println("Connection Failed!");
            e.printStackTrace();
        }
    }
}
