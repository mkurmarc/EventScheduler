package appointmentScheduler;

import appointmentScheduler.Utilities.DBConnection;
import appointmentScheduler.Utilities.DBQuery;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View_Controller/editAppointment.fxml"));
        primaryStage.setTitle("Appointment Scheduler Dashboard");
        primaryStage.setScene(new Scene(root, 1400, 800));
        primaryStage.show();
    }


    public static void main(String[] args) throws SQLException {
/*
        Connection conn = DBConnection.startConnection(); // connect to DB
        String selectStatement = "SELECT * FROM country"; // index #s of ?s from left to right = (1,2,3,4,...)

        DBQuery.setPreparedStatement(conn, selectStatement); // create PreparedStatement
        PreparedStatement ps =  DBQuery.getPreparedStatement();

        ps.execute(); // execute PreparedStatement

        ResultSet rs = ps.getResultSet();

        while (rs.next()) // while there is data in ResultSet the while loop continues
        {
            int countryId = rs.getInt("countryId");
            String countryName = rs.getString("country");
            // getDate() retrieves date from db column. toLocalDate() converts it into LocalDate type
            LocalDate date = rs.getDate("createDate").toLocalDate();
            LocalTime time = rs.getTime("createDate").toLocalTime();
            String createdBy = rs.getString("createdBy");
            LocalDateTime lastUpdate = rs.getTimestamp("lastUpdate").toLocalDateTime();
            String lastUpdateBy = rs.getString("lastUpdateBy");

            // display record
            System.out.println(countryId + " | " + countryName + " | " + date + " | " + time + " | " + createdBy + " | " + lastUpdate + " | " + lastUpdateBy);

        }

 */

        launch(args);
        //DBConnection.closeConnection(); // close DB connection
    }
}
