package appointmentScheduler.View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class editContactController implements Initializable {
    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private ComboBox<?> contactTypeComboBox;

    @FXML
    private TextField addressTextField;

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
    private Label companyNameLabel;

    @FXML
    private TextField companyNameTextField;

    @FXML
    private Button saveEditContactButton;

    @FXML
    private Button cancelEditContactButton;

    @FXML
    void cancelEditContactButtonHandler(ActionEvent event) {

    }

    @FXML
    void saveEditContactButtonHandler(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
