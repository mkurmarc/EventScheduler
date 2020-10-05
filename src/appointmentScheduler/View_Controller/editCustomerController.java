package appointmentScheduler.View_Controller;

import appointmentScheduler.DAO.Impl.AddressDaoImpl;
import appointmentScheduler.DAO.Impl.AppointmentDaoImpl;
import appointmentScheduler.DAO.Impl.CityDaoImpl;
import appointmentScheduler.DAO.Impl.CustomerDaoImpl;
import appointmentScheduler.Model.*;
import com.sun.xml.internal.bind.v2.model.core.ID;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static appointmentScheduler.Utilities.Alerts.confirmationWindow;

public class editCustomerController implements Initializable {
    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField addressTextField;

    @FXML
    private TextField addressTextField2;

    @FXML
    private TextField cityTextField;

    @FXML
    private TextField postalCodeTextField;

    @FXML
    private TextField countryTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private ComboBox<Byte> activeComboBox;

    @FXML
    private Button saveEditCustomerButton;

    @FXML
    private Button cancelEditCustomerButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int indexOfSelectedObj = dashboardController.getIndexOfSelectedObj();
        Appointment selectedObject = Appointment.getAllAppointments().get(indexOfSelectedObj);// object from user selection

        /*
        block of code gets the address ID from selected Customer object, and uses it to retrieve a Address object from
        the SQL database using an AddressDAO method.
         */
        int customerID = selectedObject.getCustomerId();
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

        /*
        block gets the country ID from selected City object, and uses it to retrieve a Country object from Countries list
        using a for loop an searching for the correct country ID.
        */
        int countryID = selectedCityObj.getCountryId();
        Country selectedCountryObj = new Country();
        for (int i=0; i < Country.getAllCountries().size(); i++) {
            if (Country.getAllCountries().get(i).getCountryId() == countryID) {
                selectedCountryObj = Country.getAllCountries().get(i);
            }
        }

        String fullName = selectedCustomerObj.getCustomerName(); // gets full name from user selected object
        int sizeFullName = fullName.length();
        String firstName = "";
        String lastName = "";
        // if statement splits full name into first and last name
        if (fullName.contains(" ")) {
            firstName = fullName.substring(0, fullName.indexOf(" ")).trim();
            lastName = fullName.substring(fullName.indexOf(" "), sizeFullName).trim();
        }

        // sets text fields from all 3 objects created
        firstNameTextField.setText(firstName);
        lastNameTextField.setText(lastName);
        addressTextField.setText(selectedAddressObj.getAddress());
        addressTextField2.setText(selectedAddressObj.getAddress2());
        cityTextField.setText(selectedCityObj.getCity());
        postalCodeTextField.setText(selectedAddressObj.getPostalCode());
        countryTextField.setText(selectedCountryObj.getCountry());
        phoneTextField.setText(selectedAddressObj.getPhone());
        activeComboBox.setValue(selectedCustomerObj.getActive());
    }

    @FXML
    void cancelEditCustomerButtonHandler(ActionEvent event) throws IOException {
        if (confirmationWindow(1)) {
            Stage stage;
            Parent root;
            stage = (Stage) cancelEditCustomerButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void saveEditCustomerButtonHandler(ActionEvent event) throws SQLException, IOException {
        int indexOfSelectedObj = dashboardController.getIndexOfSelectedObj();
        Appointment selectedObject = Appointment.getAllAppointments().get(indexOfSelectedObj); // object from user selection

//        int customerID = selectedObject.getCustomerId();
//        int addressID =
//        String title = firstNameTextField.getText() + " " + lastNameTextField.getText();

        //AppointmentDaoImpl.updateAppointment(selectedObject); // updates database

        Stage stage;
        Parent root;
        stage = (Stage) saveEditCustomerButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
