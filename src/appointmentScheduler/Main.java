package appointmentScheduler;

import appointmentScheduler.Utilities.DBConnection;
import appointmentScheduler.Utilities.DBQuery;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View_Controller/dashboard.fxml"));
        primaryStage.setTitle("Appointment Scheduler Dashboard");
        primaryStage.setScene(new Scene(root, 700, 700));
        primaryStage.show();
    }


    public static void main(String[] args) throws SQLException {

        Connection conn = DBConnection.startConnection();

        DBQuery.setStatement(conn); // create statement object
        Statement statement = DBQuery.getStatement();

        // Raw SQL insert statement
        String insertStatement = "INSERT INTO country(country, createDate, createdBy, lastUpdateBy) VALUES('USA','2020-02-22 00:00:00', 'admin', 'admin')";

        // Execute SQL statement
        statement.execute(insertStatement);

        // Confirm rows affected
        if(statement.getUpdateCount() > 0)
            System.out.println(statement.getUpdateCount() + "row(s) affected.");
        else
            System.out.println("No change.");

        launch(args);

        DBConnection.closeConnection();
    }
}
