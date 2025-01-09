/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author NANDANA BIJU
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbconnect {

    private static final String URL = "jdbc:mysql://localhost:3306/fintrack_db";
    private static final String USER = "root";  // Change with your MySQL user
    private static final String PASSWORD = "Nandana@13";  // Change with your MySQL password

    /**
     * Establishes and returns a connection to the MySQL database
     * 
     * @return a connection object for interacting with the database
     * @throws SQLException if unable to connect to the database
     */
    public static Connection getConnection() throws SQLException {
        try {
            // Ensure the MySQL driver is loaded
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Return the connection object
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            // Handle any SQL or driver class loading errors
            throw new SQLException("Database connection error: " + e.getMessage(), e);
        }
    }

    /**
     * Closes the given database connection
     * 
     * @param connection the connection to be closed
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error while closing connection: " + e.getMessage());
            }
        }
    }
}

