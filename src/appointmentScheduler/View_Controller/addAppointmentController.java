package appointmentScheduler.View_Controller;
/*
    @AUTHOR
    Marc Rios
    ID:
*/
import appointmentScheduler.DAO.Impl.AppointmentDaoImpl;
import appointmentScheduler.Model.*;
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
import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
        selectApptTypeCombo.setItems(Appointment.getAllAppointmentTypes());
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
        boolean noErrors = true;
        // uses customer object to complete creation of appointment object
        try {
            Customer customerSelected = customerSearchCombo.getValue();
            int customerID = customerSelected.getCustomerId();
            int userID  = User.getUserList().get(0).getUserId();

            // appointment ID list is created, sorted, and address ID is created by adding 1 to last item on the list
            ObservableList<Integer> apptIdList = FXCollections.observableArrayList();
            for(int i = 0; i < Appointment.getAllAppointments().size(); i++) {
                apptIdList.add(Appointment.getAllAppointments().get(i).getAppointmentId());
            }
            apptIdList = apptIdList.sorted();
            int appointmentID = apptIdList.get(apptIdList.size() - 1) + 1;

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
            block below gets LocalDate and LocalTime from picker and combo boxed to create LocalDateTime objects, start
            and end, to then use for appointment object creation
            */
            LocalDateTime start = LocalDateTime.of(apptDate, startTime);
            LocalDateTime end = LocalDateTime.of(apptDate, endTime);
            LocalDateTime createDate = LocalDateTime.now();
            String createdBy = User.getUserList().get(0).getUserName();
            LocalDateTime lastUpdate = LocalDateTime.now();
            String lastUpdateBy = User.getUserList().get(0).getUserName();

            ObservableList<Appointment> allList = FXCollections.observableArrayList(Appointment.getAllAppointments());
            // check if start/end times of user input do not interfere with the current start/end times
            for(int i=0; i < allList.size(); i++) {
                LocalDateTime existingStart = allList.get(i).getStart();
                LocalDateTime existingEnd = allList.get(i).getEnd();
                if(start.isAfter(existingStart) && end.isBefore(existingEnd)) {
                    noErrors = false;
                    Alerts.errorAppointment(17);
                    break;
                }
                else if((start.equals(existingStart) || start.equals(existingEnd))) {
                    noErrors = false;
                    Alerts.errorAppointment(17);
                    break;
                }
                else if(end.isAfter(existingStart) && end.isBefore(existingEnd)) {
                    noErrors = false;
                    Alerts.errorAppointment(18);
                    break;
                }
                else if((end.equals(existingStart) || end.equals(existingEnd))) {
                    noErrors = false;
                    Alerts.errorAppointment(18);
                    break;
                }
            }

            // checks user inputs for errors
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
                // appt object created from user input
                Appointment newApptObj = new Appointment(appointmentID, customerID, userID, title, description, location,
                        contact, type, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy);
                // creates new appt and adds it to database
                AppointmentDaoImpl.createAppointment(newApptObj);
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
            Parent parent = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
            Scene scene = new Scene(parent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
    }
}
/*
    @AUTHOR
    Marc Rios
    ID:
*/