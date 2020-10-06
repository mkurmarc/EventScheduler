package appointmentScheduler.View_Controller;

import appointmentScheduler.DAO.Impl.AddressDaoImpl;
import appointmentScheduler.DAO.Impl.CityDaoImpl;
import appointmentScheduler.DAO.Impl.CustomerDaoImpl;
import appointmentScheduler.Model.Address;
import appointmentScheduler.Model.Appointment;
import appointmentScheduler.Model.City;
import appointmentScheduler.Model.Customer;
import javafx.event.ActionEvent;
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
    public Button backToCustomersButton;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int indexOfApptObject = dashboardController.getIndexOfSelectedObj();
        Appointment selectedApptObject = Appointment.getAllAppointments().get(indexOfApptObject); // object from user selection
        /*
        block of code gets the customer ID from selected Appointment object, and uses it to retrieve a Customer object from
        the SQL database using a CustomerDAO method.
         */
        int customerID = selectedApptObject.getCustomerId();
        Customer selectedCustomerObj = new Customer();
        try {
            selectedCustomerObj = CustomerDaoImpl.getCustomer(customerID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*
        block of code gets the address ID from selected Customer object, and uses it to retrieve a Address object from
        the SQL database using an AddressDAO method.
         */
        int addressID = selectedCustomerObj.getAddressId();
        Address selectedAddressObj = new Address();
        try {
            selectedAddressObj = AddressDaoImpl.getAddress(addressID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*
        block of code gets the city ID from selected Address object, and uses it to retrieve a City object from
        the SQL database using a CityDAO method.
         */
        int cityID = selectedAddressObj.getCityId();
        City selectedCityObj = new City();
        try {
            selectedCityObj = CityDaoImpl.getCity(cityID);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // sets labels from all 3 objects created
        varFullNameLabel.setText(selectedCustomerObj.getCustomerName());
        varIdLabel.setText(String.valueOf(customerID));
        varAddress1Label.setText(selectedAddressObj.getAddress());
        varAddress2Label.setText(selectedAddressObj.getAddress2());
        varCityLabel.setText(selectedCityObj.getCity());
        varPostalCodeLabel.setText(selectedAddressObj.getPostalCode());
        varPhoneLabel.setText(selectedAddressObj.getPhone());
    }

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

    @FXML
    public void backToCustomersHandler(ActionEvent actionEvent) throws IOException {
        if (confirmationWindow(5)) {
            Stage stage;
            Parent root;
            stage = (Stage) backToCustomersButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("allCustomers.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
}
