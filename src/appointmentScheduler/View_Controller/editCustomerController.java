package appointmentScheduler.View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static appointmentScheduler.Utilities.Alerts.confirmationWindow;

public class editCustomerController {

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField addressTextField;

    @FXML
    private TextField addressTextField2;

    @FXML
    private TextField cityTextField;

    @FXML
    private TextField zipCodeTextField;

    @FXML
    private ChoiceBox<?> activeChoiceBox;

    @FXML
    private Button saveEditCustomerButton;

    @FXML
    private Button cancelEditCustomerButton;

    @FXML
    void cancelEditCustomerButtonHandler(ActionEvent event) throws IOException {
        if (confirmationWindow(1)) {
            Stage stage;
            Parent root;
            stage = (Stage) cancelEditCustomerButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void saveEditCustomerButtonHandler(ActionEvent event) {

    }

}
