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
import java.util.Scanner;

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

        String updateStatement = "UPDATE country SET country = ?, createdBy = ? WHERE country = ?"; // index #s of ?s from left to right = (1,2,3,4,...)

        DBQuery.setPreparedStatement(conn, updateStatement); // create PreparedStatement

        PreparedStatement ps = DBQuery.getPreparedStatement();

        String countryName, newCountry, createdBy;

        // Get keyboard inputs
        Scanner keyboard = new Scanner(System.in);

        System.out.print("Enter a country to update: ");
        countryName = keyboard.nextLine();

        System.out.print("Enter new country: ");
        newCountry = keyboard.nextLine();

        System.out.print("Enter user: ");
        createdBy = keyboard.nextLine();


        // key-value mapping
        ps.setString(1, newCountry);
        ps.setString(2, createdBy);
        ps.setString(3, countryName);

        ps.execute(); // execute PreparedStatement

        // check rows affected
        if (ps.getUpdateCount() > 0)
            System.out.println(ps.getUpdateCount() + "row(s) affected!");
        else
            System.out.println("No change!");

        launch(args);
        DBConnection.closeConnection(); // close DB connection
    }
}
