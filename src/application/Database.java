
package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String JDBC_CONNECTION = "jdbc:oracle:thin:@199.212.26.208:1521:SQLD";
    private static final String JDBC_USER = "COMP228_M23_sy_69";
    private static final String JDBC_PASSWORD = "password";

    static Connection conn = null;

    // Method to establish a database connection
    public static void dbConnect() throws SQLException, ClassNotFoundException {
        // If the connection is null or closed, attempt to create a new connection
        if (conn == null || conn.isClosed()) {
            try {
                // Load the Oracle JDBC driver
                Class.forName(JDBC_DRIVER);
                // Create a new connection using the JDBC URL, username, and password
                conn = DriverManager.getConnection(JDBC_CONNECTION, JDBC_USER, JDBC_PASSWORD);
            } catch (ClassNotFoundException e) {
                System.out.println("Failed to connect to the database!");
                e.printStackTrace();
                throw e;
            }
            System.out.println("Connected to the database successfully!");
        }
    }

    // Method to disconnect from the database
    public static void dbDisconnect() throws SQLException {
        // If the connection is not null and not closed, close the connection
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
}