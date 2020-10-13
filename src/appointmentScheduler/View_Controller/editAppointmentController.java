package appointmentScheduler.View_Controller;

import appointmentScheduler.DAO.Impl.AddressDaoImpl;
import appointmentScheduler.DAO.Impl.AppointmentDaoImpl;
import appointmentScheduler.DAO.Impl.CityDaoImpl;
import appointmentScheduler.DAO.Impl.CustomerDaoImpl;
import appointmentScheduler.Main;
import appointmentScheduler.Model.Address;
import appointmentScheduler.Model.Appointment;
import appointmentScheduler.Model.City;
import appointmentScheduler.Model.Customer;
import appointmentScheduler.Utilities.Alerts;
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
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static appointmentScheduler.Utilities.Alerts.confirmationWindow;

public class editAppointmentController implements Initializable {


    @FXML
    private ComboBox<Customer> customerSearchCombo;

    @FXML
    private DatePicker appointmentDatePicker;

    @FXML
    private ComboBox<String> selectApptTypeCombo;

    @FXML
    private ComboBox<LocalTime> startTimeCombo;

    @FXML
    private ComboBox<LocalTime> endTimeCombo;

    @FXML
    private TextField titleTextField;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private TextField locationTextField;

    @FXML
    private Button saveEditApptButton;

    @FXML
    private Button cancelEditApptButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int indexOfApptObject = dashboardController.getIndexOfSelectedObj();
        Appointment selectedApptObject = Appointment.getAllAppointments().get(indexOfApptObject); // object from user selection
        int customerID = selectedApptObject.getCustomerId();

        Customer selectedCustomerObj = new Customer();
        try {
            selectedCustomerObj = CustomerDaoImpl.getCustomer(customerID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // code below this comment sets the items and values for each fxml element
        customerSearchCombo.setItems(Customer.getAllCustomers());
        customerSearchCombo.setValue(selectedCustomerObj);
        appointmentDatePicker.setValue(selectedApptObject.getStart().toLocalDate());
        LocalTime startTime = LocalTime.of(8, 0);
        LocalTime endTime = LocalTime.of(17, 0);
        // while loop generates the local time list for the start and end times
        while (startTime.isBefore(endTime.plusSeconds(1))) {
            startTimeCombo.getItems().add(startTime);
            endTimeCombo.getItems().add(startTime);
            startTime = startTime.plusMinutes(15);
        }
        // removes start time of 17:00 because that is business closing time
        startTimeCombo.getItems().remove(startTimeCombo.getItems().size() - 1);
        //removes end time of 08:00 because that is the business opening time
        endTimeCombo.getItems().remove(endTimeCombo.getItems().remove(0));
        startTimeCombo.setValue(LocalTime.from(selectedApptObject.getStart()));
        endTimeCombo.setValue(LocalTime.from(selectedApptObject.getEnd()));
        selectApptTypeCombo.setItems(Appointment.getAllAppointmentTypes());
        selectApptTypeCombo.setValue(selectedApptObject.getType());
        titleTextField.setText(selectedApptObject.getTitle());
        descriptionTextField.setText(selectedApptObject.getDescription());
        locationTextField.setText(selectedApptObject.getLocation());

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
    void saveEditApptButtonHandler(ActionEvent event) throws IOException {
        boolean noErrors = true;
        try {
            int indexOfSelectedObj = dashboardController.getIndexOfSelectedObj();
            Appointment selectedCustomerObj = Appointment.getAllAppointments().get(indexOfSelectedObj); // object from user selection

            int appointmentID = selectedCustomerObj.getAppointmentId();
            int customerID = selectedCustomerObj.getCustomerId();
            int userID = selectedCustomerObj.getUserId();
            String title = titleTextField.getText();
            String description = descriptionTextField.getText();
            String location = locationTextField.getText();
            String contact = selectedCustomerObj.getContact();
            String type = selectApptTypeCombo.getValue();
            String url = selectedCustomerObj.getUrl();
        /*
        block below gets LocalDate and LocalTime from picker and combo boxes to create LocalDateTime objects, start and
        end, to then use them for appointment object creation
        */
            LocalDate apptDate = appointmentDatePicker.getValue();
            LocalTime startTime = startTimeCombo.getValue();
            LocalTime endTime = endTimeCombo.getValue();
            LocalDateTime start = LocalDateTime.of(apptDate, startTime);
            LocalDateTime end = LocalDateTime.of(apptDate, endTime);
            // gets the last four parameters from the user selected customer object for appointment object creation
            LocalDateTime createDate = selectedCustomerObj.getCreateDate();
            String createdBy = selectedCustomerObj.getCreatedBy();
            LocalDateTime lastUpdate = LocalDateTime.now();
            String lastUpdateBy = selectedCustomerObj.getLastUpdateBy();

            // check if start/end times of user input do not interfere with the current start/end times
            for(int i=0; i < Appointment.getAllAppointments().size(); i++) {
                LocalTime existingStartTime = Appointment.getAllAppointments().get(i).getStartTime();
                LocalTime existingEndTime = Appointment.getAllAppointments().get(i).getEndTime();
                if(startTime.isAfter(existingStartTime) && startTime.isBefore(existingEndTime)) {
                    noErrors = false;
                    Alerts.errorAppointment(17);
                }
                else if(startTime.equals(existingStartTime) || startTime.equals(existingEndTime)) {
                    noErrors = false;
                    Alerts.errorAppointment(17);
                }

                else if(endTime.isAfter(existingStartTime) && endTime.isBefore(existingEndTime)) {
                    noErrors = false;
                    Alerts.errorAppointment(18);
                }
                else if(endTime.equals(existingStartTime) || endTime.equals(existingEndTime)) {
                    noErrors = false;
                    Alerts.errorAppointment(18);
                }
            }

            // checks user inputs for errors
            if(startTime.isAfter(endTime) || endTime.isBefore(startTime)) {
                noErrors = false;
                Alerts.errorAppointment(19);
            }
            if(startTime.equals(endTime)) {
                noErrors = false;
                Alerts.errorAppointment(20);
            }
            if(title.length() > 255) {
                noErrors = false;
                Alerts.errorAppointment(7);
            }
            if(title.isEmpty()) {
                noErrors = false;
                Alerts.errorAppointment(15);
            }
            if(description.length() > 300) {
                noErrors = false;
                Alerts.errorAppointment(8);
            }
            if(location.length() > 45) {
                noErrors = false;
                Alerts.errorAppointment(9);
            }

            if(noErrors) {
                Appointment updatedApptObj = new Appointment(appointmentID, customerID, userID, title, description, location,
                        contact, type, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy);

                // updates appt in database
                AppointmentDaoImpl.updateAppointment(updatedApptObj);
            }
        }
        catch (NullPointerException e) {
            noErrors = false;
            if(customerSearchCombo.getValue() == null) Alerts.errorAppointment(2);
            else if(appointmentDatePicker.getValue() == null) Alerts.errorAppointment(3);
            else if(startTimeCombo.getValue() == null) Alerts.errorAppointment(5);
            else if(endTimeCombo.getValue() == null) Alerts.errorAppointment(6);
        }
        catch (SQLIntegrityConstraintViolationException e) {
            noErrors = false;
            if (selectApptTypeCombo.getValue() == null) Alerts.errorAppointment(4);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        // below block changes screen to dashboard
        if(noErrors) {
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
}
