package appointmentScheduler.View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class dashboardController implements Initializable {
    @FXML
    private MenuBar menuBarHome;

    @FXML
    private Menu reportsMenuBar;

    @FXML
    private Menu closeMenuBar;

    @FXML
    private DatePicker datePickerHome;

    @FXML
    private RadioButton viewAllRadioButton;

    @FXML
    private RadioButton viewWeekRadioButton;

    @FXML
    private RadioButton viewMonthRadioButton;

    @FXML
    private Label varDateLabel;

    @FXML
    private TableView<?> appointmentsTableView;

    @FXML
    private TableColumn<?, ?> dateColumn;

    @FXML
    private TableColumn<?, ?> timeColumn;

    @FXML
    private TableColumn<?, ?> appointmentColumn;

    @FXML
    private TableColumn<?, ?> titleColumn;

    @FXML
    private TableColumn<?, ?> descriptionColumn;

    @FXML
    private TableColumn<?, ?> typeColumn;

    @FXML
    private TableColumn<?, ?> userIdColumn;

    @FXML
    private TableColumn<?, ?> locationColumn;

    @FXML
    private TableColumn<?, ?> durationColumn;

    @FXML
    private Button viewCustomerButton;

    @FXML
    private Button addCustomerButton;

    @FXML
    private Button editCustomerButton;

    @FXML
    private Button deleteCustomerButton;

    @FXML
    private Button addAppointmentButton;

    @FXML
    private Button editAppointmentButton;

    @FXML
    private Button deleteAppointmentButton;

    @FXML
    void addAppointmentButtonHandler(ActionEvent event) {

    }

    @FXML
    void addCustomerButtonHandler(ActionEvent event) {

    }

    @FXML
    void closeMenuBarActionHandler(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Exit application?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.exit(0);
        }
    }

    @FXML
    void datePickerHomeHandler(ActionEvent event) {

    }

    @FXML
    void deleteAppointmentButtonHandler(ActionEvent event) {

    }

    @FXML
    void deleteCustomerButtonHandler(ActionEvent event) {

    }

    @FXML
    void editAppointmentButtonHandler(ActionEvent event) {

    }

    @FXML
    void editCustomerButtonHandler(ActionEvent event) {

    }

    @FXML
    void reportsMenuBarActionHandler(ActionEvent event) {

    }

    @FXML
    void viewAllRadioButtonHandler(ActionEvent event) {

    }

    @FXML
    void viewCustomerButtonHandler(ActionEvent event) {

    }

    @FXML
    void viewMonthRadioButtonHandler(ActionEvent event) {

    }

    @FXML
    void viewWeekRadioButtonHandler(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
