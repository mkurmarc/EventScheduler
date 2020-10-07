package appointmentScheduler.View_Controller;

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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

    @FXML
    void loginButtonHandler(ActionEvent event) throws IOException {
        String userName = loginUsernameTextField.getText();
        String password = loginPasswordField.getText();

        if (userName == null) {
            Alerts.loginError(1, loginUsernameTextField, loginPasswordField);
        }
        if (password == null) {
            Alerts.loginError(2, loginUsernameTextField, loginPasswordField);
        }
        if (userName != null && userName.length() > 20) {
            Alerts.loginError(3, loginUsernameTextField, loginPasswordField);
        }
        if (password != null && password.length() > 20) {
            Alerts.loginError(4, loginUsernameTextField, loginPasswordField);
        }
        if (checkLoginCredentials(userName, password)) {
            // sets current user
            //User.setCurrentUserName(userName);
            // open dashboard fxml file
            Stage stageDashboard;
            Parent root;
            stageDashboard = (Stage) loginButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stageDashboard.setScene(scene);
            stageDashboard.show();
        }
        if (!checkLoginCredentials(userName, password)) {
            Alerts.loginError(5, loginUsernameTextField, loginPasswordField);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ResourceBundle rb = ResourceBundle.getBundle("appointmentScheduler/Nat", Locale.getDefault());

            if (Locale.getDefault().getLanguage().equals("es") || Locale.getDefault().getLanguage().equals("de") ||
                    Locale.getDefault().getLanguage().equals("fr")) {
                loginLabel.setText(rb.getString("Login"));
                loginUsernameTextField.setText(rb.getString("login"));
                loginUsernameTextField.setText(rb.getString("username"));
                loginPasswordField.setText(rb.getString("password"));
            }
        }
        catch(MissingResourceException e) {
            e.getMessage();
        }

        try {
            Country.setAllCountries(CountryDaoImpl.getAllCountry());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
