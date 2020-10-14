package appointmentScheduler.View_Controller;

import appointmentScheduler.DAO.Impl.AddressDaoImpl;
import appointmentScheduler.DAO.Impl.CityDaoImpl;
import appointmentScheduler.DAO.Impl.CustomerDaoImpl;
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
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import static appointmentScheduler.Utilities.Alerts.confirmationWindow;

public class addCustomerController implements Initializable {
    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField address1TextField;

    @FXML
    private TextField address2TextField;

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
    private Button saveAddCustomerButton;

    @FXML
    private Button cancelAddCustomerButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            City.setAllCities(CityDaoImpl.getAllCities());
            Address.setAllAddresses(AddressDaoImpl.getAllAddresses());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ObservableList<Byte> activeList = FXCollections.observableArrayList();
        activeList.add((byte) 0);
        activeList.add((byte) 1);

        countryCombo.setItems(Country.getAllCountries());
        activeComboBox.setItems(activeList);
    }

    @FXML
    void cancelAddCustomerButtonHandler(ActionEvent event) throws IOException {
        if (confirmationWindow(1)) {
            Stage stage;
            Parent root;
            stage = (Stage) cancelAddCustomerButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("allCustomers.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void saveAddCustomerButtonHandler(ActionEvent event) throws IOException {
        boolean errorsPresent = false;
        try {
            // customer ID list is created, sorted, and customer ID is created by adding 1 to last item on the list
            ObservableList<Integer> customerIdList = FXCollections.observableArrayList();
            for(int i=0; i < Customer.getAllCustomers().size(); i++) {
                customerIdList.add(Customer.getAllCustomers().get(i).getAddressId());
            }
            customerIdList = customerIdList.sorted();
            int customerID = customerIdList.get(customerIdList.size() - 1) + 1;

            String customerFirstName = firstNameTextField.getText();
            String customerLastName = lastNameTextField.getText();
            String customerName = customerFirstName + " " + customerLastName;
            byte active = activeComboBox.getValue();

            // address ID list is created, sorted, and address ID is created by adding 1 to last item on the list
            ObservableList<Integer> addressIdList = FXCollections.observableArrayList();
            for(int i=0; i < Address.getAllAddresses().size(); i++) {
                addressIdList.add(Address.getAllAddresses().get(i).getAddressId());
            }
            addressIdList = addressIdList.sorted();
            int addressId = addressIdList.get(addressIdList.size() - 1) + 1;

            String address1 = address1TextField.getText();
            String address2 = address2TextField.getText();
            String postalCode = postalCodeTextField.getText();
            String phone = phoneTextField.getText();

            int cityId = 1;
            boolean found = false;
            City existingCity = new City();
            String cityName = cityTextField.getText();
            for(int i=0; i < City.getAllCities().size(); i++) {
                if(cityName.equals(City.getAllCities().get(i).getCity())) {
                    existingCity = City.getAllCities().get(i);
                    found = true;
                }
            }
            if(!found) { // maybe change logic for this if statement, look to other id creation logic
                for(int i=0; i < City.getAllCities().size(); i++) {
                    if(City.getAllCities().get(i).getCityId() == cityId) {
                        cityId++;
                    } else break;
                }
            }

            int countryID = countryCombo.getValue().getCountryId();
            // gets the last four parameters for all object creation in this handler
            LocalDateTime createDate = LocalDateTime.now();
            String createdBy = User.getUserList().get(0).getUserName();
            LocalDateTime lastUpdate = LocalDateTime.now();
            String lastUpdateBy = User.getUserList().get(0).getUserName();

            // block alerts users if inputs incorrect
            if(firstNameTextField.getText().isEmpty() || lastNameTextField.getText().isEmpty()) {
                errorsPresent = true;
                Alerts.errorCustomer(11);
            }
            if(customerName.length() > 45) {
                errorsPresent = true;
                Alerts.errorCustomer(2);
            }
            if(address1.isEmpty()) {
                errorsPresent = true;
                Alerts.errorCustomer(13);
            }
            if(address1.length() > 50) {
                errorsPresent = true;
                Alerts.errorCustomer(4);
            }
            if(address2.isEmpty()) {
                errorsPresent = true;
                Alerts.errorCustomer(14);
            }
            if(address2.length() > 50) {
                errorsPresent = true;
                Alerts.errorCustomer(5);
            }
            if(cityName.isEmpty()) {
                errorsPresent = true;
                Alerts.errorCustomer(15);
            }
            if(cityName.length() > 50) {
                errorsPresent = true;
                Alerts.errorCustomer(6);
            }
            if(postalCode.isEmpty()) {
                errorsPresent = true;
                Alerts.errorCustomer(16);
            }
            if(postalCode.length() > 10) {
                errorsPresent = true;
                Alerts.errorCustomer(3);
            }
            if(phone.isEmpty()) {
                errorsPresent = true;
                Alerts.errorCustomer(17);
            }
            if(phone.length() > 20) {
                errorsPresent = true;
                Alerts.errorCustomer(12);
            }

            /*
            if the city object already exists in the all city list and no errors present, then this creates a new city
            object and adds it to DB
             */
            if(!found && !errorsPresent) {
                City addCity = new City(cityId, cityName, countryID, createDate, createdBy, lastUpdate, lastUpdateBy);
                System.out.println("Saving to database...");
                CityDaoImpl.addCity(addCity);
                Address addAddress = new Address(addressId, address1, address2, cityId, postalCode, phone, createDate, createdBy,
                        lastUpdate, lastUpdateBy);
                AddressDaoImpl.addAddress(addAddress);
                Customer addCustomer = new Customer(customerID, customerName, addressId, active, createDate, createdBy,
                        lastUpdate, lastUpdateBy);
                CustomerDaoImpl.addCustomer(addCustomer);
            }
            // if city object found on list and no errors present, then uses existing city object
            else if (found && !errorsPresent) {
                Address addAddress = new Address(addressId, address1, address2, existingCity.getCityId(), postalCode,
                        phone, createDate, createdBy, lastUpdate, lastUpdateBy);
                System.out.println("Saving to database...");
                AddressDaoImpl.addAddress(addAddress);
                Customer addCustomer = new Customer(customerID, customerName, addressId, active, createDate, createdBy,
                        lastUpdate, lastUpdateBy);
                CustomerDaoImpl.addCustomer(addCustomer);
            }
        }
        catch (NullPointerException e) {
            if(countryCombo.getValue() == null) {
                errorsPresent = true;
                Alerts.errorCustomer(10);
            }
            if(activeComboBox.getValue() == null) {
                errorsPresent = true;
                Alerts.errorCustomer(9);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        // changes scene if no errors present
        if(!errorsPresent) {
            Parent parent = FXMLLoader.load(getClass().getResource("allCustomers.fxml"));
            Scene scene = new Scene(parent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
    }
}
