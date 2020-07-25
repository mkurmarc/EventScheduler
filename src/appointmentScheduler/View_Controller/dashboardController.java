package appointmentScheduler.View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class dashboardController {
    @FXML
    private Button previousWeekButton;

    @FXML
    private Button nextWeekButton;

    @FXML
    private Button monthViewButtonHandler;

    @FXML
    private Button searchContactsButton;

    @FXML
    private TextField searchContactTextField;

    @FXML
    private Button addContactButton;

    @FXML
    private Button editContactButton;

    @FXML
    private Button deleteContactButton;

    @FXML
    private TableView<?> contactsTableView;

    @FXML
    private TableColumn<?, ?> contactCustomerIDColumn;

    @FXML
    private TableColumn<?, ?> contactFullNameColumn;

    @FXML
    private TableColumn<?, ?> contactAddressColumn;

    @FXML
    private TableColumn<?, ?> contactPhoneNumberColumn;

    @FXML
    private Button searchApptsButton;

    @FXML
    private TextField searchAppointmentsTextField;

    @FXML
    private Button appointmentAddButton;

    @FXML
    private Button appointmentEditButton;

    @FXML
    private Button appointmentDeleteButton;

    @FXML
    private TableView<?> appointmentsTableView;

    @FXML
    private TableColumn<?, ?> appointmentCustomerIDColumn;

    @FXML
    private TableColumn<?, ?> appointmentFullNameColumn;

    @FXML
    private TableColumn<?, ?> appointmentTypeColumn;

    @FXML
    private TableColumn<?, ?> appointmentPhoneNumberColumn;

    @FXML
    private Label monthVariableLabel;

    @FXML
    private Label weekVariableLabel;

    @FXML
    private Label yearVariableLabel;

    @FXML
    private ListView<?> mondayListView;

    @FXML
    private ListView<?> tuesdayListView;

    @FXML
    private ListView<?> wednesdayListView;

    @FXML
    private ListView<?> thursdayListView;

    @FXML
    private ListView<?> fridayListView;

    @FXML
    private ListView<?> saturdayListView;

    @FXML
    private ListView<?> sundayListView;

    @FXML
    void addContactButtonHandler(ActionEvent event) {

    }

    @FXML
    void appointmentAddButtonHandler(ActionEvent event) {

    }

    @FXML
    void appointmentDeleteButtonHandler(ActionEvent event) {

    }

    @FXML
    void appointmentEditButtonHandler(ActionEvent event) {

    }

    @FXML
    void deleteContactButtonHandler(ActionEvent event) {

    }

    @FXML
    void editContactButtonHandler(ActionEvent event) {

    }

    @FXML
    void monthViewButtonHandler(ActionEvent event) {

    }

    @FXML
    void nextWeekButtonHandler(ActionEvent event) {

    }

    @FXML
    void previousWeekButtonHandler(ActionEvent event) {

    }

    @FXML
    void searchContactsButtonHandler(ActionEvent event) {

    }

    @FXML
    void searchApptsButtonHandler(ActionEvent event) {

    }
}
