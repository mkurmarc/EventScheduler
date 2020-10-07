package appointmentScheduler.View_Controller;

import appointmentScheduler.DAO.Impl.AppointmentDaoImpl;
import appointmentScheduler.Model.Appointment;
import appointmentScheduler.Model.Customer;
import appointmentScheduler.Model.User;
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
import java.time.format.DateTimeFormatter;
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
    private TextField startTimeTextField;

    @FXML
    private TextField endTimeTextField;

    @FXML
    private TextField titleTextField;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private RadioButton startTimeAMPeriod;

    @FXML
    private RadioButton startTimePMPeriod;

    @FXML
    private Button saveAddApptButton;

    @FXML
    private Button cancelAddApptButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customerSearchCombo.setItems(Customer.getAllCustomers());
        selectApptTypeCombo.setItems(dashboardController.getAllAppointmentTypes());
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
    void saveAddApptButtonHandler(ActionEvent event) {
        // uses customer object to complete creation of appointment object
        Customer customerSelected = customerSearchCombo.getValue();
        int customerID = customerSelected.getCustomerId();
        int userID  = User.getUserList().get(0).getUserId();
        // utilize the appointmentID list to create a non duplicate ID??
        int appointmentID = 123; // hard code because mySQL generates its own apptID

        String title = titleTextField.getText();
        String description = descriptionTextField.getText();
        String location = "";
        String contact = "";
        String url = "";
        /*
        block below gets LocalDate and LocalTime from picker and text fields to create LocalDateTime objects, start and
        end, to then use for appointment object creation
        */
        LocalDate apptDate = appointmentDatePicker.getValue();
        LocalTime startTime = LocalTime.parse(startTimeTextField.getText());
        LocalTime endTime = LocalTime.parse(endTimeTextField.getText());
        LocalDateTime start = LocalDateTime.of(apptDate, startTime);
        LocalDateTime end = LocalDateTime.of(apptDate, endTime);
        String type = selectApptTypeCombo.getValue();

        DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String startDateString = start.format(dtfDate);

        DateTimeFormatter dtfTime = DateTimeFormatter.ofPattern("HH:mm a");
        String startTimeString = start.format(dtfTime);
        String endTimeString = end.format(dtfTime);

        LocalDateTime createDate = LocalDateTime.now();
        String createdBy = User.getCurrentUserName();
        LocalDateTime lastUpdate = LocalDateTime.now();
        String lastUpdateBy = User.getCurrentUserName();

        Appointment newApptObj = new Appointment(appointmentID, customerID, userID, title, description, location,
                contact, type, url, start, startDateString, startTimeString, end, endTimeString, createDate, createdBy,
                lastUpdate, lastUpdateBy);

        try {
            AppointmentDaoImpl.createAppointment(newApptObj);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
