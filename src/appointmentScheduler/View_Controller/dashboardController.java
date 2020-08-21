package appointmentScheduler.View_Controller;

import appointmentScheduler.Model.Appointment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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

    private int indexEditCustomer;
    private int indexEditAppt;

    public int getIndexEditCustomer() {
        return indexEditCustomer;
    }

    public int getIndexEditAppt() {
        return indexEditAppt;
    }



    @FXML
    void addAppointmentButtonHandler(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) addAppointmentButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addAppointment.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void addCustomerButtonHandler(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) addCustomerButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addCustomer.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
    void editAppointmentButtonHandler(ActionEvent actionEvent) throws IOException {
        /*
        Appointment editAppt = (Appointment) appointmentsTableView.getSelectionModel().getSelectedItem();
        if (editAppt != null) {
            indexEditAppt dfsf;
        }

        Parent root;
        root = FXMLLoader.load(getClass().getResource("scheduleReports.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

         */
    }

    @FXML
    void editCustomerButtonHandler(ActionEvent event) {

    }

    @FXML
    void reportsMenuBarActionHandler(ActionEvent actionEvent) throws IOException {

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
        // Appointments table and columns
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("start"));

    }
}
