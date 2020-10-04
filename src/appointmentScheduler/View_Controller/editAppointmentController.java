package appointmentScheduler.View_Controller;

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
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.util.ResourceBundle;

import static appointmentScheduler.Utilities.Alerts.confirmationWindow;

public class editAppointmentController implements Initializable {
    private static ObservableList<String> allCustomersNames = FXCollections.observableArrayList(); // list for combo box
    private static ObservableList<Byte> allAppointmentTypes = FXCollections.observableArrayList(); // list for combo box

    @FXML
    private ComboBox<String> customerSearchCombo;

    @FXML
    private DatePicker appointmentDatePicker;

    @FXML
    private ComboBox<Byte> selectApptTypeCombo;

    @FXML
    private ComboBox<LocalTime> startTimeCombo;

    @FXML
    private RadioButton startTimeAMPeriod;

    @FXML
    private RadioButton startTimePMPeriod;

    @FXML
    private ComboBox<LocalTime> endTimeCombo;

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
        // sets the toggle groups
        startTimeAMPeriod.setToggleGroup(startPeriodToggleGroup);
        startTimePMPeriod.setToggleGroup(startPeriodToggleGroup);
        endTimeAMPeriod.setToggleGroup(endPeriodToggleGroup);
        endTimePMPeriod.setToggleGroup(endPeriodToggleGroup);
        // 2 lines below adds the options to the list
        allAppointmentTypes.add((byte) 0);
        allAppointmentTypes.add((byte) 1);

        // for loop adds the customer names to a list
        for (int i=0; i < Customer.getAllCustomers().size(); i++) {
            allCustomersNames.add(Customer.getAllCustomers().get(i).getCustomerName());
        }

        int indexOfApptObject = dashboardController.getIndexOfApptObject();
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
        customerSearchCombo.setItems(allCustomersNames);
        customerSearchCombo.setValue(selectedCustomerObj.getCustomerName());
        appointmentDatePicker.setValue(selectedApptObject.getStartDate());
        selectApptTypeCombo.setItems(allAppointmentTypes);
        selectApptTypeCombo.setValue(selectedCustomerObj.getActive());
        startTimeCombo.setItems(TimeClass.getListOfTimes());
        startTimeCombo.setValue(selectedApptObject.getStartTime());
        endTimeCombo.setItems(TimeClass.getListOfTimes());
        endTimeCombo.setValue(selectedApptObject.getEnd());
        titleTextField.setText(selectedApptObject.getTitle());
        descriptionTextField.setText(selectedApptObject.getDescription());

        // block of code below checks time and toggles appropriate radio button
        LocalTime amTime = LocalTime.of(11,59);
        if (selectedApptObject.getStartTime().isAfter(amTime)) {
            startPeriodToggleGroup.selectToggle(startTimePMPeriod);
        } else {
            startPeriodToggleGroup.selectToggle(startTimeAMPeriod);
        }

        if (selectedApptObject.getEnd().isBefore(amTime)) {
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void saveEditApptButtonHandler(ActionEvent event) {

    }
}
