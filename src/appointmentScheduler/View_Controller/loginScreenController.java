package appointmentScheduler.View_Controller;
/*
    @AUTHOR
    Marc Rios
    ID:
*/
import appointmentScheduler.DAO.Impl.CountryDaoImpl;
import appointmentScheduler.Model.Country;
import appointmentScheduler.Model.User;
import appointmentScheduler.Utilities.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import static appointmentScheduler.Model.User.checkLoginCredentials;

public class loginScreenController implements Initializable {
    @FXML
    private TextField loginUsernameTextField;

    @FXML
    private PasswordField loginPasswordField;

    @FXML
    private Button loginButton;

    @FXML
    private Label loginLabel;

    private static final String filename = "appointmentScheduler/Resources/Nat_es";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ResourceBundle rb = ResourceBundle.getBundle(filename, Locale.getDefault());
            if(Locale.getDefault().getLanguage().equals("es")) {
                loginLabel.setText(rb.getString("Login"));
                loginUsernameTextField.setPromptText(rb.getString("username"));
                loginPasswordField.setPromptText(rb.getString("password"));
                loginButton.setText(rb.getString("login"));
            }
        } catch(MissingResourceException e) {
            System.out.println(e.getMessage());
        }

        try {
            Country.setAllCountries(CountryDaoImpl.getAllCountry());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void loginButtonHandler(ActionEvent event) throws IOException {
        String userName = loginUsernameTextField.getText();
        String password = loginPasswordField.getText();
        // error checks and  messages if language is english default
        if(!Locale.getDefault().getLanguage().equals("es")) {
            if(!checkLoginCredentials(userName, password)) {
                Alerts.loginError(5, loginUsernameTextField, loginPasswordField);
            }
        }

        // error checks and messages if language is spanish default
        if (Locale.getDefault().getLanguage().equals("es")) {
            if (!checkLoginCredentials(userName, password)) {
                Alerts.spanishLoginError(5,loginUsernameTextField,loginPasswordField);
            }
        }

        if (checkLoginCredentials(userName, password)) {
            // open dashboard fxml file is username and password match
            Stage stageDashboard;
            Parent root;
            stageDashboard = (Stage) loginButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stageDashboard.setScene(scene);
            stageDashboard.show();

            String filename = "src/appointmentScheduler/Files/log_file.txt", item;
            FileWriter fwriter = new FileWriter(filename, true);
            PrintWriter outputFile = new PrintWriter(fwriter);
            String loggedUser = String.valueOf(User.getUserList().get(0).getUserName());
            Timestamp loggedTime = Timestamp.valueOf(LocalDateTime.now());
            item = loggedUser + " " + loggedTime;
            // writes to login text file the user and timestamp of login time
            outputFile.println(item);
            // close file
            outputFile.close();
        }
    }
}
/*
    @AUTHOR
    Marc Rios
    ID:
*/