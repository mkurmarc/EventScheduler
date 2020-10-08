package appointmentScheduler.View_Controller;

import appointmentScheduler.DAO.Impl.AppointmentDaoImpl;
import appointmentScheduler.Model.Appointment;
import appointmentScheduler.Model.Customer;
import appointmentScheduler.Model.User;
import appointmentScheduler.Utilities.Alerts;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;

import static appointmentScheduler.Utilities.Alerts.confirmationWindow;


public class addAppointmentController implements Initializable {
    @FXML
    private ComboBox<Customer> customerSearchCombo;

    @FXML
    private DatePicker appointmentDatePicker;

    @FXML
    private ComboBox<String> selectApptTypeCombo;

    @FXML
    public ComboBox<LocalTime> startTimeCombo;

    @FXML
    public ComboBox<LocalTime> endTimeCombo;

    @FXML
    private TextField titleTextField;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private TextField locationTextField;

    @FXML
    private RadioButton startTimeAMPeriod;

    @FXML
    private RadioButton startTimePMPeriod;

    @FXML
    private RadioButton endTimeAMPeriod;

    @FXML
    private RadioButton endTimePMPeriod;

    @FXML
    private Button saveAddApptButton;

    @FXML
    private Button cancelAddApptButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customerSearchCombo.setItems(Customer.getAllCustomers());
        selectApptTypeCombo.setItems(dashboardController.getAllAppointmentTypes());
        LocalTime startTime = LocalTime.of(8,0);
        LocalTime endTime = LocalTime.of(17,0);
        // while loop generates the local time list for the start and end times
        while(startTime.isBefore(endTime.plusSeconds(1))) {
            startTimeCombo.getItems().add(startTime);
            endTimeCombo.getItems().add(startTime);
            startTime = startTime.plusMinutes(15);
        }
        // removes start time of 17:00 because that is business closing time
        startTimeCombo.getItems().remove(startTimeCombo.getItems().size()-1);
        //removes end time of 08:00 because that is the business opening time
        endTimeCombo.getItems().remove(endTimeCombo.getItems().remove(0));

        startTimeCombo.setPromptText("Select time");
        endTimeCombo.setPromptText("Select time");
        startTimeCombo.setVisibleRowCount(6);
        endTimeCombo.setVisibleRowCount(6);
    }

    @FXML
    void cancelAddApptButtonHandler(ActionEvent event) throws IOException {
        if (confirmationWindow(1)) {
            Stage stage;
            Parent root;
            stage = (Stage) cancelAddApptButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void saveAddApptButtonHandler(ActionEvent event) throws IOException {
        // uses customer object to complete creation of appointment object
        Customer customerSelected = customerSearchCombo.getValue();
        int customerID = customerSelected.getCustomerId();
        int userID  = User.getUserList().get(0).getUserId();
        int appointmentID = 123; // hard code because mySQL generates its own apptID


        LocalDate apptDate = appointmentDatePicker.getValue();
        LocalTime start = startTimeCombo.getValue();
        LocalTime end = endTimeCombo.getValue();
        String type = selectApptTypeCombo.getValue();
        String title = titleTextField.getText();
        String description = descriptionTextField.getText();
        String location = locationTextField.getText();
        String contact = " ";
        String url = " ";

        /*
        block below gets LocalDate and LocalTime from picker and text fields to create LocalDateTime objects, start and
        end, to then use for appointment object creation
        */
        DateTimeFormatter dtfTime = DateTimeFormatter.ofPattern("HH:mm a");
        DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");




//        LocalTime startTime = LocalTime.parse(startTimeTextField.getText());
//        LocalTime endTime = LocalTime.parse(endTimeTextField.getText());
//        LocalDateTime start = LocalDateTime.of(apptDate, startTime);
//        LocalDateTime end = LocalDateTime.of(apptDate, endTime);
//        String startDateString = start.format(dtfDate);
//        String startTimeString = start.format(dtfTime);
//        String endTimeString = end.format(dtfTime);

        LocalDateTime createDate = LocalDateTime.now();
        String createdBy = User.getUserList().get(0).getUserName();
        LocalDateTime lastUpdate = LocalDateTime.now();
        String lastUpdateBy = User.getUserList().get(0).getUserName();

        LocalTime openTime = LocalTime.of(8,0);
        LocalTime closeTime = LocalTime.of(5, 0);
/*
        if (startTime.isBefore(openTime) || startTime.isAfter(closeTime.minusMinutes(15))) {
            Alerts.errorAppointment(17, startTimeTextField);
        }
        if (endTime.isBefore(openTime) || endTime.isAfter(closeTime)) {
            Alerts.errorAppointment(18, endTimeTextField);
        }
        if (title.equals("")) {
            Alerts.errorAppointment(15, titleTextField);
        }
        if (description.equals("")) {
            Alerts.errorAppointment(16, descriptionTextField);
        }
        if (title.length() > 50) {
            Alerts.errorAppointment(7, titleTextField);
        }
        if (description.length() > 100) {
            Alerts.errorAppointment(8, descriptionTextField);
        }

 */

        Appointment newApptObj = new Appointment(appointmentID, customerID, userID, title, description, location,
                contact, type, url, start, startDateString, startTimeString, end, endTimeString, createDate, createdBy,
                lastUpdate, lastUpdateBy);

//        try {
//            AppointmentDaoImpl.createAppointment(newApptObj);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        Parent parent = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void startTimeComboHandler(ActionEvent actionEvent) {

    }

    public void endTimeComboHandler(ActionEvent actionEvent) {

    }
}
