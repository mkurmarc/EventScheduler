package appointmentScheduler.View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class addContactController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private ChoiceBox<?> contactTypeChoiceBox;

    @FXML
    private TextField addressTextField;

    @FXML
    private TextField cityTextField;

    @FXML
    private ChoiceBox<?> stateChoiceBox;

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
    private Button saveAddContactButton;

    @FXML
    private Button cancelAddContactButton;

    @FXML
    void cancelAddContactButtonHandler(ActionEvent event) {

    }

    @FXML
    void saveAddContactButtonHandler(ActionEvent event) {

    }
}
