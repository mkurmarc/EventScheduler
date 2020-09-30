package appointmentScheduler.View_Controller;

import appointmentScheduler.DAO.Impl.AddressDaoImpl;
import appointmentScheduler.DAO.Impl.CityDaoImpl;
import appointmentScheduler.DAO.Impl.CustomerDaoImpl;
import appointmentScheduler.Model.Address;
import appointmentScheduler.Model.Appointment;
import appointmentScheduler.Model.City;
import appointmentScheduler.Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static appointmentScheduler.Utilities.Alerts.confirmationWindow;

public class customerInformationController implements Initializable {

//    ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
//    ObservableList<Address> allAddresses = FXCollections.observableArrayList();
//    ObservableList<City> allCities = FXCollections.observableArrayList();

    @FXML
    private Label varFullNameLabel;

    @FXML
    private Label varIdLabel;

    @FXML
    private Label varAddress1Label;

    @FXML
    private Label varAddress2Label;

    @FXML
    private Label varCityLabel;

    @FXML
    private Label varPostalCodeLabel;

    @FXML
    private Label varPhoneLabel;

    @FXML
    private Button backButton;

    private static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

    @FXML
    public void backButtonHandler() throws IOException {
        if (confirmationWindow(2)) {
            Stage stage;
            Parent root;
            stage = (Stage) backButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*
        Block below gets users selection from dashboard and transfers information of selected
        appointment to the text fields in customer information screen
        */
        Customer customerObj;
        customerObj = dashboardController.getAllCustomers().get(dashboardController.getIndexOfObject());
        int customerID = customerObj.getCustomerId(); // gets the customer ID from dashboard user selection

        Address addressObj = new Address();
        try {
            addressObj = AddressDaoImpl.getAddress(customerID);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        City cityObj = new City();
        try {
            cityObj = CityDaoImpl.getCity(addressObj.getCityId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        varFullNameLabel.setText(customerObj.getCustomerName());
        varIdLabel.setText(String.valueOf(customerID));
        varAddress1Label.setText(addressObj.getAddress());
        varAddress2Label.setText(addressObj.getAddress2());
        varCityLabel.setText(cityObj.getCity());
        varPostalCodeLabel.setText(addressObj.getPostalCode());
        varPhoneLabel.setText(addressObj.getPhone());

    }
}
