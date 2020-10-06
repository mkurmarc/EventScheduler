package appointmentScheduler.View_Controller;

import appointmentScheduler.DAO.Impl.*;
import appointmentScheduler.Model.*;
import appointmentScheduler.Utilities.Alerts;
import appointmentScheduler.Utilities.TimeClass;
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
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
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
    private DatePicker datePickerAppointments;

    @FXML
    private RadioButton viewAllRadioButton;

    @FXML
    private RadioButton viewWeekRadioButton;

    @FXML
    private RadioButton viewMonthRadioButton;

    @FXML
    private Label varDateLabel;

    @FXML
    private TableView<Appointment> appointmentsTableView;

    @FXML
    private TableColumn<Appointment, LocalDate> dateAppointmentColumn;

    @FXML
    private TableColumn<Appointment, LocalTime> startTimeAppointmentColumn;

    @FXML
    private TableColumn<Appointment, LocalTime> endTimeAppointmentColumn;

    @FXML
    private TableColumn<Appointment, Integer> appointmentIdColumn;

    @FXML
    private TableColumn<Appointment, String> titleColumn;

    @FXML
    private TableColumn<Appointment, String> descriptionColumn;

    @FXML
    private TableColumn<Appointment, String> typeColumn;

    @FXML
    private TableColumn<Appointment, Integer> customerIdColumn;

    @FXML
    private TableColumn<Appointment, String> locationColumn;

    @FXML
    private Button viewAllCustomerButton;

    @FXML
    private Button viewCustomerButton;

    @FXML
    private Button editCustomerButton;

    @FXML
    private Button addAppointmentButton;

    @FXML
    private Button editAppointmentButton;

    @FXML
    private Button deleteAppointmentButton;

    private static int indexOfSelectedObj;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Appointments table and columns
        dateAppointmentColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        startTimeAppointmentColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endTimeAppointmentColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

        // retrieve data from database and sets appointment list
        try {
            Appointment.setAllAppointments(AppointmentDaoImpl.getAllAppointments()); // transfers data and sets from SQL server to list
            Country.setAllCountries(CountryDaoImpl.getAllCountry());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        appointmentsTableView.setItems(Appointment.getAllAppointments());
    }

    // getter for index to be modified which allows access to other layers of the program
    public static int getIndexOfSelectedObj() {
        return indexOfSelectedObj;
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
    public void viewAllCustomersHandler(ActionEvent actionEvent) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) viewAllCustomerButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("allCustomers.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void viewCustomerButtonHandler(ActionEvent actionEvent) throws IOException {
        Appointment viewCustomer = appointmentsTableView.getSelectionModel().getSelectedItem();
        if (viewCustomer != null) {
            indexOfSelectedObj = Appointment.getAllAppointments().indexOf(viewCustomer);

            Stage stage;
            Parent root;
            stage = (Stage) viewCustomerButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("customerInformation.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else {
            Alerts.selectionError(2);
        }
    }

    @FXML
    void editCustomerButtonHandler(ActionEvent actionEvent) throws IOException {
        Appointment userSelectedObj = appointmentsTableView.getSelectionModel().getSelectedItem();
        if (userSelectedObj != null) {
            indexOfSelectedObj = Appointment.getAllAppointments().indexOf(userSelectedObj);
            Stage stage;
            Parent root;
            stage = (Stage) editCustomerButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("editCustomer.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else {
            Alerts.selectionError(4);
        }
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
    void deleteAppointmentButtonHandler(ActionEvent event) throws SQLException {
        Appointment deleteAppointment = appointmentsTableView.getSelectionModel().getSelectedItem();
        if (deleteAppointment != null) {
            if (Alerts.confirmationWindow(3)) {
                AppointmentDaoImpl.deleteAppointment(deleteAppointment);
                Appointment.getAllAppointments().remove(deleteAppointment);
                appointmentsTableView.setItems(Appointment.getAllAppointments());
            }
        }
        else {
            Alerts.selectionError(1);
        }
    }

    @FXML
    void editAppointmentButtonHandler(ActionEvent actionEvent) throws IOException {
        Appointment editAppt = appointmentsTableView.getSelectionModel().getSelectedItem();
        if (editAppt != null) {
            indexOfSelectedObj = Appointment.getAllAppointments().indexOf(editAppt);
            Parent root;
            root = FXMLLoader.load(getClass().getResource("editAppointment.fxml"));
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } else {
            Alerts.selectionError(3);
        }
    }

    @FXML
    void reportsMenuBarActionHandler(ActionEvent actionEvent) throws IOException {

    }

    // REMOVE HANDLERS FOR RADIO BUTTONS???
    @FXML
    void viewAllRadioButtonHandler(ActionEvent event) {

    }

    @FXML
    void viewMonthRadioButtonHandler(ActionEvent event) {

    }

    @FXML
    void viewWeekRadioButtonHandler(ActionEvent event) {

    }
}
