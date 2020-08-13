package appointmentScheduler.View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static appointmentScheduler.Utilities.Alerts.confirmationWindow;

public class editAppointmentController implements Initializable {
    @FXML
    private RadioButton existingContactRadioButton;

    @FXML
    private ToggleGroup existingOrNewCustomer;

    @FXML
    private RadioButton newContactRadioButton;

    @FXML
    private ComboBox<?> customerSearchCombo;

    @FXML
    private DatePicker appointmentDatePicker;

    @FXML
    private ComboBox<?> selectApptTypeCombo;

    @FXML
    private ComboBox<?> selectStartTimeCombo;

    @FXML
    private ComboBox<?> selectEndTimeCombo;

    @FXML
    private TextField titleTextField;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField addressTextField;

    @FXML
    private TextField address2TextField;

    @FXML
    private TextField cityTextField;

    @FXML
    private TextField zipCodeTextField;

    @FXML
    private Button saveEditApptButton;

    @FXML
    private Button cancelEditApptButton;

    @FXML
    void cancelEditApptButtonHandler(ActionEvent event) throws IOException {
        if (confirmationWindow(1)) {
            Stage stage;
            Parent root;
            stage = (Stage) cancelEditApptButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void saveEditApptButtonHandler(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
