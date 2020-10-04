package appointmentScheduler.View_Controller;

import appointmentScheduler.DAO.Impl.AddressDaoImpl;
import appointmentScheduler.DAO.Impl.CityDaoImpl;
import appointmentScheduler.Model.Address;
import appointmentScheduler.Model.Appointment;
import appointmentScheduler.Model.City;
import appointmentScheduler.Model.Customer;
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
import java.util.ResourceBundle;

import static appointmentScheduler.Utilities.Alerts.confirmationWindow;

public class editAppointmentController implements Initializable {

    @FXML
    private ComboBox<String> customerSearchCombo;

    @FXML
    private DatePicker appointmentDatePicker;

    @FXML
    private ComboBox<Byte> selectApptTypeCombo;

    @FXML
    private ComboBox<?> startTimeCombo;

    @FXML
    private ComboBox<?> endTimeCombo;

    @FXML
    private TextField titleTextField;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private Button saveEditApptButton;

    @FXML
    private Button cancelEditApptButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> allCustomersNames = FXCollections.observableArrayList(); // list for combo box
        ObservableList<Byte> allAppointmentTypes = FXCollections.observableArrayList(); // list for combo box
        // 2 lines below adds the options to the list
        allAppointmentTypes.add((byte) 0);
        allAppointmentTypes.add((byte) 1);

        for (int i=0; i < Customer.getAllCustomers().size(); i++) {
            allCustomersNames.add(Customer.getAllCustomers().get(i).getCustomerName());

        }

        Appointment appointmentObj = new Appointment();
        appointmentObj = Appointment.getAllAppointments().get(dashboardController.getIndexOfApptObject());

        Customer customerObj;
        customerObj = Customer.getAllCustomers().get(dashboardController.getIndexOfApptObject());

        Address addressObj = new Address();
        City cityObj = new City();
        try {
            addressObj = AddressDaoImpl.getAddress(customerObj.getCustomerId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            cityObj = CityDaoImpl.getCity(addressObj.getCityId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        customerSearchCombo.setItems(allCustomersNames);
        customerSearchCombo.setValue(customerObj.getCustomerName());
        selectApptTypeCombo.setItems(allAppointmentTypes);
        selectApptTypeCombo.setValue(customerObj.getActive());
        //appointmentDatePicker.setValue();

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
