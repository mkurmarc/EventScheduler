package appointmentScheduler.View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


public class addAppointmentController implements Initializable {
    @FXML
    private DatePicker appointmentDatePicker;

    @FXML
    private ComboBox<?> selectTimeComboBox;

    @FXML
    private ComboBox<?> selectApptTypeComboBox;

    @FXML
    private RadioButton existingContactRadioButton;

    @FXML
    private RadioButton newContactRadioButton;

    @FXML
    private TextField contactSearchTextField;

    @FXML
    private Button contactSearchButton;

    @FXML
    private Button selectContactButton;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private ComboBox<?> contactTypeComboBox;

    @FXML
    private TextField addressTextField;

    @FXML
    private TextField address2TextField;

    @FXML
    private TextField cityTextField;

    @FXML
    private ComboBox<?> stateComboBox;

    @FXML
    private TextField zipCodeTextField;

    @FXML
    private TextField areaCodeTextField;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private TextField companyNameTextField;

    @FXML
    private TextArea notesTextArea;

    @FXML
    private Button saveAddApptButton;

    @FXML
    private Button cancelAddApptButton;

    @FXML
    void cancelAddApptButtonHandler(ActionEvent event) {

    }

    @FXML
    void contactSearchButtonHandler(ActionEvent event) {

    }

    @FXML
    void saveAddApptButtonHandler(ActionEvent event) {

    }

    @FXML
    void selectContactButtonHandler(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
