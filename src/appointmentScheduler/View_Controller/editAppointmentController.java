package appointmentScheduler.View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

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
    private TextArea descriptionTextArea;

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
    void cancelEditApptButtonHandler(ActionEvent event) {

    }

    @FXML
    void saveEditApptButtonHandler(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
