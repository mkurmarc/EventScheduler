package appointmentScheduler.View_Controller;

import appointmentScheduler.DAO.Impl.*;
import appointmentScheduler.Model.*;
import appointmentScheduler.Utilities.Alerts;
import com.sun.xml.internal.bind.v2.model.core.ID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
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
    private ComboBox<Country> countryCombo;

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
        int indexOfSelectedObj = allCustomersController.getIndexOfSelectedCustomer();
        Customer selectedCustomerObj = new Customer();
        selectedCustomerObj = Customer.getAllCustomers().get(indexOfSelectedObj);
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

        ObservableList<Byte> activeList = FXCollections.observableArrayList();
        activeList.add((byte) 0);
        activeList.add((byte) 1);
        // sets text fields from all 3 objects created
        firstNameTextField.setText(firstName);
        lastNameTextField.setText(lastName);
        addressTextField.setText(selectedAddressObj.getAddress());
        addressTextField2.setText(selectedAddressObj.getAddress2());
        cityTextField.setText(selectedCityObj.getCity());
        postalCodeTextField.setText(selectedAddressObj.getPostalCode());
        countryCombo.setItems(Country.getAllCountries());
        countryCombo.setValue(selectedCountryObj);
        phoneTextField.setText(selectedAddressObj.getPhone());
        activeComboBox.setItems(activeList);
        activeComboBox.setValue(selectedCustomerObj.getActive());
    }

    @FXML
    void cancelEditCustomerButtonHandler(ActionEvent event) throws IOException {
        if (confirmationWindow(1)) {
            Stage stage;
            Parent root;
            stage = (Stage) cancelEditCustomerButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("customerInformation.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void saveEditCustomerButtonHandler(ActionEvent event) throws SQLException, IOException {
        try {
            int indexOfSelectedObj = allCustomersController.getIndexOfSelectedCustomer();
            Customer selectedCustomerObj = Customer.getAllCustomers().get(indexOfSelectedObj);
            // gets address obj from mySQL server
            Address selectedAddressObj = AddressDaoImpl.getAddress(selectedCustomerObj.getAddressId());

            // next few lines get data from user and from user selected customer object for creation of new customer object
            int customerID = selectedCustomerObj.getCustomerId();
            int cityID = selectedAddressObj.getCityId();
            String customerName = firstNameTextField.getText() + " " + lastNameTextField.getText();
            int addressID = selectedCustomerObj.getAddressId();

            // next few lines get date from user for creation of new address object
            String address1 = addressTextField.getText();
            String address2 = addressTextField2.getText();
            String city = cityTextField.getText();
            Country country = countryCombo.getValue();
            String postalCode = postalCodeTextField.getText();
            String phoneNo = phoneTextField.getText();
            int countryID = country.getCountryId();
            Byte active = activeComboBox.getValue();
            // get city object using for loop
//            City selectedCityObj = new City();
//            for (int i=0; i < City.getAllCities().size(); i++) {
//                if (City.getAllCities().get(i).getCountryId() == cityID) {
//                    selectedCityObj = City.getAllCities().get(i);
//                }
//            }
            
//            // get country object using for loop
//            Country selectedCountryObj = new Country();
//            for (int i=0; i < Country.getAllCountries().size(); i++) {
//                if (Country.getAllCountries().get(i).getCountryId() == countryID) {
//                    selectedCountryObj = Country.getAllCountries().get(i);
//                }
//            }

            // gets the last four parameters for all object creation in this handler
            LocalDateTime createDate = selectedCustomerObj.getCreateDate();
            String createdBy = selectedCustomerObj.getCreatedBy();
            LocalDateTime lastUpdate = LocalDateTime.now();
            String lastUpdateBy = selectedCustomerObj.getLastUpdateBy();

            // create objects for mySQL updates
            Customer editedCustomerObj = new Customer(customerID, customerName, addressID, active, createDate, createdBy,
                    lastUpdate, lastUpdateBy);
            Address editedAddressObj = new Address(addressID, address1, address2, cityID, postalCode, phoneNo, createDate,
                    createdBy, lastUpdate, lastUpdateBy);
            City editedCityObj = new City(cityID, city, countryID, createDate, createdBy, lastUpdate, lastUpdateBy);

            // customer dao update statement
            CustomerDaoImpl.updateCustomer(editedCustomerObj);
            // address dao update statement
            AddressDaoImpl.updateAddress(editedAddressObj);
            // city dao update statement
            CityDaoImpl.updateCity(editedCityObj);

            // below block changes screen to dashboard
            Stage stage;
            Parent root;
            stage = (Stage) saveEditCustomerButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (NullPointerException e) {
            System.out.println("Null Pointer Ex: " + e.getMessage());
            if(countryCombo.getValue() == null) Alerts.errorCustomer(10);
            else if(activeComboBox.getValue() == null) Alerts.errorAppointment(9);
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("SQLIntegrityConstraintViolationException" + e.getMessage());
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
}
