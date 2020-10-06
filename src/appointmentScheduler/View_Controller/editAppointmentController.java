package appointmentScheduler.View_Controller;

import appointmentScheduler.DAO.Impl.AppointmentDaoImpl;
import appointmentScheduler.DAO.Impl.CustomerDaoImpl;
import appointmentScheduler.Model.Appointment;
import appointmentScheduler.Model.Customer;
import appointmentScheduler.Utilities.TimeClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static appointmentScheduler.Utilities.Alerts.confirmationWindow;

public class editAppointmentController implements Initializable {


    @FXML
    private ComboBox<String> customerSearchCombo;

    @FXML
    private DatePicker appointmentDatePicker;

    @FXML
    private ComboBox<String> selectApptTypeCombo;

    @FXML
    private TextField startTimeTextField;

    @FXML
    private RadioButton startTimeAMPeriod;

    @FXML
    private RadioButton startTimePMPeriod;

    @FXML
    private TextField endTimeTextField;

    @FXML
    private RadioButton endTimeAMPeriod;

    @FXML
    private RadioButton endTimePMPeriod;

    @FXML
    private TextField titleTextField;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private Button saveEditApptButton;

    @FXML
    private Button cancelEditApptButton;

    ToggleGroup startPeriodToggleGroup = new ToggleGroup();
    ToggleGroup endPeriodToggleGroup = new ToggleGroup();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> allCustomersNames = FXCollections.observableArrayList(); // list for combo box
        ObservableList<String> allAppointmentTypes = FXCollections.observableArrayList(); // list for combo box
        // sets the toggle groups
        startTimeAMPeriod.setToggleGroup(startPeriodToggleGroup);
        startTimePMPeriod.setToggleGroup(startPeriodToggleGroup);
        endTimeAMPeriod.setToggleGroup(endPeriodToggleGroup);
        endTimePMPeriod.setToggleGroup(endPeriodToggleGroup);
        // lines below adds the options to the list
        allAppointmentTypes.add("Training"); // fix must be a string not byte
        allAppointmentTypes.add("Presentation");
        allAppointmentTypes.add("Scrum");
        allAppointmentTypes.add("Code Review");
        allAppointmentTypes.add("Meeting");

        // for loop adds the customer names to a list
        for (int i=0; i < Customer.getAllCustomers().size(); i++) {
            allCustomersNames.add(Customer.getAllCustomers().get(i).getCustomerName());
        }

        int indexOfApptObject = dashboardController.getIndexOfSelectedObj();
        Appointment selectedApptObject = Appointment.getAllAppointments().get(indexOfApptObject); // object from user selection

        int customerID = selectedApptObject.getCustomerId();
        Customer selectedCustomerObj = new Customer();
        try {
            selectedCustomerObj = CustomerDaoImpl.getCustomer(customerID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*
        int addressID = selectedCustomerObj.getAddressId();
        Address selectedAddressObj = new Address();
        try {
            selectedAddressObj = AddressDaoImpl.getAddress(addressID);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        int cityID = selectedAddressObj.getCityId();
        City selectedCityObj = new City();
        try {
            selectedCityObj = CityDaoImpl.getCity(cityID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        */
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm a");

        customerSearchCombo.setItems(allCustomersNames);
        customerSearchCombo.setValue(selectedCustomerObj.getCustomerName());
        appointmentDatePicker.setValue(selectedApptObject.getStart().toLocalDate());
        selectApptTypeCombo.setItems(allAppointmentTypes);
        selectApptTypeCombo.setValue(selectedApptObject.getType());

        startTimeTextField.setText(selectedApptObject.getStart().format(dtf));
        endTimeTextField.setText(selectedApptObject.getEnd().format(dtf));

        titleTextField.setText(selectedApptObject.getTitle());
        descriptionTextField.setText(selectedApptObject.getDescription());

        // block of code below checks time and toggles appropriate radio button
        LocalDateTime selectedStartDateTime = selectedApptObject.getStart();
        LocalTime selectedStartTime = selectedStartDateTime.toLocalTime();

        LocalDateTime selectedEndDateTime = selectedApptObject.getEnd();
        LocalTime selectedEndTime = selectedEndDateTime.toLocalTime();

        LocalTime amTime = LocalTime.of(11,59);

        if (selectedStartTime.isAfter(amTime)) {
            startPeriodToggleGroup.selectToggle(startTimePMPeriod);
        } else {
            startPeriodToggleGroup.selectToggle(startTimeAMPeriod);
        }

        if (selectedEndTime.isBefore(amTime)) {
            endPeriodToggleGroup.selectToggle(endTimeAMPeriod);
        } else {
            endPeriodToggleGroup.selectToggle(endTimePMPeriod);
        }
    }

    @FXML
    void cancelEditApptButtonHandler(ActionEvent event) throws IOException {
        if (confirmationWindow(1)) {
            Stage stage;
            Parent root;
            stage = (Stage) cancelEditApptButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("allCustomers.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void saveEditApptButtonHandler(ActionEvent event) throws SQLException, IOException {
        int indexOfSelectedObj = dashboardController.getIndexOfSelectedObj();
        Appointment selectedObject = Appointment.getAllAppointments().get(indexOfSelectedObj); // object from user selection

        int appointmentID = selectedObject.getAppointmentId();
        int customerID = selectedObject.getCustomerId();
        int userID = selectedObject.getUserId();
        String title = titleTextField.getText();
        String description = descriptionTextField.getText();
        String location = selectedObject.getLocation();
        String contact = selectedObject.getContact();
        String type = selectApptTypeCombo.getValue();
        String url = selectedObject.getUrl();
        /*
        block below gets LocalDate and LocalTime from picker and text fields to create LocalDateTime objects, start and
        end, to then use for appointment object creation
        */
        LocalDate apptDate = appointmentDatePicker.getValue();
        LocalTime startTime = LocalTime.parse(startTimeTextField.getText());
        LocalTime endTime = LocalTime.parse(endTimeTextField.getText());
        LocalDateTime start = LocalDateTime.of(apptDate, startTime);
        LocalDateTime end = LocalDateTime.of(apptDate, endTime);

        DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String startDateString = start.format(dtfDate);

        DateTimeFormatter dtfTime = DateTimeFormatter.ofPattern("HH:mm a");
        String startTimeString = start.format(dtfTime);
        String endTimeString = end.format(dtfTime);

        LocalDateTime createDate = selectedObject.getCreateDate();
        String createdBy = selectedObject.getCreatedBy();
        LocalDateTime lastUpdate = LocalDateTime.now();
        String lastUpdateBy = selectedObject.getLastUpdateBy();

        Appointment updatedApptObj = new Appointment(appointmentID, customerID, userID, title, description, location,
                contact, type, url, start, startDateString, startTimeString, end, endTimeString, createDate, createdBy,
                lastUpdate, lastUpdateBy);

        AppointmentDaoImpl.updateAppointment(updatedApptObj);

        Stage stage;
        Parent root;
        stage = (Stage) saveEditApptButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
