package appointmentScheduler.View_Controller;

import appointmentScheduler.DAO.Impl.AddressDaoImpl;
import appointmentScheduler.DAO.Impl.AppointmentDaoImpl;
import appointmentScheduler.DAO.Impl.CityDaoImpl;
import appointmentScheduler.DAO.Impl.CustomerDaoImpl;
import appointmentScheduler.Model.*;
import appointmentScheduler.Utilities.Alerts;
import appointmentScheduler.Utilities.TimeClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private TableColumn<?, ?> dateAppointmentColumn;

    @FXML
    private TableColumn<?, ?> startTimeAppointmentColumn;

    @FXML
    private TableColumn<?, ?> endTimeAppointmentColumn;

    @FXML
    private TableColumn<?, ?> appointmentIdColumn;

    @FXML
    private TableColumn<?, ?> titleColumn;

    @FXML
    private TableColumn<?, ?> descriptionColumn;

    @FXML
    private TableColumn<?, ?> typeColumn;

    @FXML
    private TableColumn<?, ?> customerIdColumn;

    @FXML
    private TableColumn<?, ?> locationColumn;

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

    private static int indexOfObject;

    // getter for index to be modified which allows access to other layers of the program
    public static int getIndexOfApptObject() {
        return indexOfObject;
    }

    @FXML
    void viewCustomerButtonHandler(ActionEvent actionEvent) throws IOException {
        Appointment viewCustomer = appointmentsTableView.getSelectionModel().getSelectedItem();
        if (viewCustomer != null) {
            indexOfObject = Appointment.getAllAppointments().indexOf(viewCustomer);

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
    void deleteCustomerButtonHandler(ActionEvent event) {
        // check if customer has appt. if they do, send error message. if not, then delete customer.
    }

    @FXML
    void editAppointmentButtonHandler(ActionEvent actionEvent) throws IOException {
        Appointment editAppt = appointmentsTableView.getSelectionModel().getSelectedItem();
        if (editAppt != null) {
            indexOfObject = Appointment.getAllAppointments().indexOf(editAppt);
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
    void editCustomerButtonHandler(ActionEvent event) {

    }

    @FXML
    void reportsMenuBarActionHandler(ActionEvent actionEvent) throws IOException {

    }

    @FXML
    void viewAllRadioButtonHandler(ActionEvent event) {

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
        dateAppointmentColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        startTimeAppointmentColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endTimeAppointmentColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

        // retrieve data from database and convert to lists for each table
        try {
//            if (Appointment.getAllAppointments().size() != 0) {
//                for (int i=0; i < Appointment.getAllAppointments().size(); i++ ) {
//                    if (!Appointment.getAllAppointments().contains(Appointment.getAllAppointments().get(i))) {
//                        Appointment.getAllAppointments().add(Appointment.getAllAppointments().get(i));
//                    }
//                }
//            } else {
//                Appointment.getAllAppointments().addAll(AppointmentDaoImpl.getAllAppointments());
//            }
            Appointment.setAllAppointments(AppointmentDaoImpl.getAllAppointments()); // transfers data and sets from SQL server to list
            //Customer.setAllCustomers(CustomerDaoImpl.getAllCustomers()); // transfers data and sets from SQL server to list
            //Address.setAllAddresses(AddressDaoImpl.getAllAddresses()); // transfers and sets data from SQL server to list
        } catch (SQLException e) {
            e.printStackTrace();
        }
        appointmentsTableView.setItems(Appointment.getAllAppointments());
        LocalTime late = LocalTime.parse("10:00:00");
        TimeClass.getListOfTimes().add(late);
    }
}
