package appointmentScheduler.Utilities;
/*
    @AUTHOR
    Marc Rios
    ID:
*/
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBQuery {
    
    // statement reference
    private static Statement statement;

    // create statement object
    public static void setStatement(Connection conn) throws SQLException {
        statement = conn.createStatement();
    }

    // return statement object
    public static Statement getStatement() {
        return statement;
    }
}
/*
    @AUTHOR
    Marc Rios
    ID:
*/