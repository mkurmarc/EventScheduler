package appointmentScheduler;
/*
    @AUTHOR
    Marc Rios
    ID:
*/
import appointmentScheduler.Model.Appointment;
import appointmentScheduler.Model.User;
import appointmentScheduler.Utilities.DBConnection;
import appointmentScheduler.Utilities.DBQuery;
import com.sun.javaws.IconUtil;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.annotation.Resource;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // below admin user is created and added to the userList
        User adminUser = new User(1, "admin", "12345", (byte) 1, LocalDateTime.now(),
                "admin", LocalDateTime.now(), "admin");
        User.addUser(adminUser);

        Parent root = FXMLLoader.load(getClass().getResource("View_Controller/loginScreen.fxml"));
        primaryStage.setTitle("Appointment Scheduler Dashboard");
        primaryStage.setScene(new Scene(root, 816, 536));
        primaryStage.show();
    }

    public static void main(String[] args) {
        //Connection conn = DBConnection.startConnection(); // connect to DB
        launch(args);
        //DBConnection.closeConnection(); // close DB connection
    }
}
/*
    @AUTHOR
    Marc Rios
    ID:
*/