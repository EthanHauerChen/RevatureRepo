package org.example;

import org.example.util.ConnectionHandler;

import java.sql.*;

public class Main {
    private Connection connection = ConnectionHandler.getConnection();

    public static void main(String[] args) {
        addMember();
    }

    public void addMember() {
        //add row to member table
        String sql = "INSERT INTO member VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "P001");
            stmt.setInt(2, 123);
            stmt.setString(3, "Silver");
            stmt.setNull(4, Types.DATE);

            try (ResultSet rs = stmt.executeQuery()) {}
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
