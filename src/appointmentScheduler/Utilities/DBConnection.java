package appointmentScheduler.Utilities;
/*
    @AUTHOR
    Marc Rios
    ID:
*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // JDBC URL parts
    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipAddress = "//wgudb.ucertify.com/U06381";

    // JDBC URL
    private static final String jdbcURL = protocol + vendorName + ipAddress;

    // Driver Interface reference
    private static final String MySQLJDBCDriver = "com.mysql.cj.jdbc.Driver";
    private static Connection conn = null;

    // Username
    private static final String username = "U06381";
    // Password
    private static final String password = "53688676865";

    public static Connection startConnection() {
        try {
            Class.forName(MySQLJDBCDriver);
            conn = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Connection Successful!");

        }
        catch (ClassNotFoundException e) {
            System.out.println("Class not found error: " + e.getMessage());
        }
        catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return conn;
    }

    public static void closeConnection() {
        try{
            conn.close();
            System.out.println("Connection Closed!");
        }
        catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
/*
    @AUTHOR
    Marc Rios
    ID:
*/