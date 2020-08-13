package appointmentScheduler.View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static appointmentScheduler.Utilities.Alerts.confirmationWindow;

public class addCustomerController implements Initializable {

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField address1TextField;

    @FXML
    private TextField address2TextField;

    @FXML
    private TextField cityTextField;

    @FXML
    private TextField zipCodeTextField;

    @FXML
    private Label companyNameLabel;

    @FXML
    private ChoiceBox<?> activeChoice;

    @FXML
    private Button saveAddCustomerButton;

    @FXML
    private Button cancelAddCustomerButton;

    @FXML
    void cancelAddCustomerButtonHandler(ActionEvent event) throws IOException {
        if (confirmationWindow(1)) {
            Stage stage;
            Parent root;
            stage = (Stage) cancelAddCustomerButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void saveAddCustomerButtonHandler(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
