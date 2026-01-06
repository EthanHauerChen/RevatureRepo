package org.example.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionHandler {
    private static Connection connection;

    //we want a single connection point to our db, so we statically initialize it
    static {
        if (connection == null) {
            //get db properties such as password and url
            Properties properties = new Properties();
            try (InputStream input = ConnectionHandler.class.getClassLoader().getResourceAsStream("database.properties")) {
                if (input == null) {
                    throw new Exception("Unable to find database.properties");
                } else {
                    properties.load(input);
                }

                //actually load the jdbc driver
                //https://dev.mysql.com/doc/connector-j/en/connector-j-usagenotes-connect-drivermanager.html
                //according to mysql documentation
                // The newInstance() call is a work around for some broken Java implementations
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

                connection = DriverManager.getConnection(
                        properties.getProperty("db.url"),
                        properties.getProperty("db.username"),
                        properties.getProperty("db.password")
                );
            }
            catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException("failed to load database configuration");
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static Connection getConnection() throws RuntimeException {
        if (connection == null) {
            throw new RuntimeException("Failed to setup database connection");
        }
        return connection;
    }
}
