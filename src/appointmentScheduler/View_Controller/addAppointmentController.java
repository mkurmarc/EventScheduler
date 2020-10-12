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
import java.sql.SQLIntegrityConstraintViolationException;
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
        try {
            Customer customerSelected = customerSearchCombo.getValue();
            int customerID = customerSelected.getCustomerId();
            int userID  = User.getUserList().get(0).getUserId();
            int appointmentID = 123; // hard code because mySQL generates its own apptID

            LocalDate apptDate = appointmentDatePicker.getValue();
            LocalTime startTime = startTimeCombo.getValue();
            LocalTime endTime = endTimeCombo.getValue();
            String type = selectApptTypeCombo.getValue();
            String title = titleTextField.getText();
            String description = descriptionTextField.getText();
            String location = locationTextField.getText();
            String contact = " ";
            String url = " ";
        /*
        block below gets LocalDate and LocalTime from picker and combo boxed to create LocalDateTime objects, start and
        end, to then use for appointment object creation
        */
            LocalDateTime start = LocalDateTime.of(apptDate, startTime);
            LocalDateTime end = LocalDateTime.of(apptDate, endTime);
            LocalDateTime createDate = LocalDateTime.now();
            String createdBy = User.getUserList().get(0).getUserName();
            LocalDateTime lastUpdate = LocalDateTime.now();
            String lastUpdateBy = User.getUserList().get(0).getUserName();

            Appointment newApptObj = new Appointment(appointmentID, customerID, userID, title, description, location,
                    contact, type, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy);

            AppointmentDaoImpl.createAppointment(newApptObj);

            Parent parent = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
            Scene scene = new Scene(parent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();

        } catch (NullPointerException e) {
            //System.out.println("Null Pointer Ex: " + e.getMessage());
            if(customerSearchCombo.getValue() == null) Alerts.errorAppointment(2);
            else if(appointmentDatePicker.getValue() == null) Alerts.errorAppointment(3);
            else if(startTimeCombo.getValue() == null) Alerts.errorAppointment(5);
            else if(endTimeCombo.getValue() == null) Alerts.errorAppointment(6);
        }
        catch (SQLIntegrityConstraintViolationException e) {
            //System.out.println("SQLIntegrityConstraintViolationException" + e.getMessage());
            if (selectApptTypeCombo.getValue() == null) Alerts.errorAppointment(4);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
