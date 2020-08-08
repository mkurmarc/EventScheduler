package appointmentScheduler.Utilities;
/*
    @AUTHOR
    Marc Rios
    ID:
*/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DBQuery {
    
    // statement reference
    private static PreparedStatement statement;

    // create statement object
    public static void setPreparedStatement(Connection conn, String sqlStatement) throws SQLException {

        statement = conn.prepareStatement(sqlStatement);
    }

    // return statement object
    public static PreparedStatement getPreparedStatement() {
        return statement;
    }
}
/*
    @AUTHOR
    Marc Rios
    ID:
*/