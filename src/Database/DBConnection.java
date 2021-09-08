package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This DBConnection class handles the connection to the database via JDBC.
 */
public class DBConnection {
    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipAddress = "//wgudb.ucertify.com:3306/";
    private static final String dbName = "WJ05Zvi";
    private static final String jdbcURL = protocol + vendorName + ipAddress + dbName + "?connectionTimeZone=SERVER";
    private static final String MYSQLJDBCDriver = "com.mysql.cj.jdbc.Driver";
    private static final String username = "U05Zvi";
    private static final String password = "53688651099";
    private static Connection conn = null;

    /**
     * This void method starts a connection with the database with username, password and database url.
     */
    public static void startConnection() throws SQLException {
        try {
            Class.forName(MYSQLJDBCDriver);
            conn = DriverManager.getConnection(jdbcURL, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * This void method gets a connection whenever SQL query is made.
     */
    public static Connection getConnection() {
        return conn;
    }

    /**
     * This static void method used to end connection after terminating the program.
     */
    public static void closeConnection() {
        try {
            conn.close();
            System.out.println("Connection Closed!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean validCredentials(String text, String text1) {
        return false;
    }
}

