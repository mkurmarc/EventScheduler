package appointmentScheduler;

import appointmentScheduler.Utilities.DBConnection;
import appointmentScheduler.Utilities.DBQuery;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View_Controller/dashboard.fxml"));
        primaryStage.setTitle("Appointment Scheduler Dashboard");
        primaryStage.setScene(new Scene(root, 700, 700));
        primaryStage.show();
    }


    public static void main(String[] args) throws SQLException {

        Connection conn = DBConnection.startConnection(); // connect to DB
        DBQuery.setStatement(conn); // create statement object
        Statement statement = DBQuery.getStatement(); // get statement reference

        String selectStatement = "SELECT * FROM country"; // SELECT statement
        statement.execute(selectStatement); // execute statement
        ResultSet rs = statement.getResultSet(); // get result set

        // below - forward scroll ResultSet
        while (rs.next()) // while there is data in ResultSet the while loop continues
        {
            int countryId = rs.getInt("countryId");
            String countryName = rs.getString("country");
            LocalDate date = rs.getDate("createDate").toLocalDate(); // getDate() retrieves date from db column. toLocalDate() converts it into LocalDate type
            LocalTime time = rs.getTime("createDate").toLocalTime();
            String createdBy = rs.getString("createdBy");
            LocalDateTime lastUpdate = rs.getTimestamp("lastUpdate").toLocalDateTime();
            String lastUpdateBy = rs.getString("lastUpdateBy");

            // display record
            System.out.println(countryId + " | " + countryName + " | " + date + " | " + time + " | " + createdBy + " | " + lastUpdate + " | " + lastUpdateBy);

        }


        launch(args);

        DBConnection.closeConnection(); // close DB connection
    }
}
