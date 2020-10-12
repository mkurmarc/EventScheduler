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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("customerInformation.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void saveAddCustomerButtonHandler(ActionEvent event) {
        boolean noErrors = true;
        try {
            int customerID = 0;
            for(int i=0; i < Customer.getAllCustomers().size(); i++) {
                if(Customer.getAllCustomers().get(i).getAddressId() == customerID) {
                    customerID++;
                } else break;
            }

            String customerFirstName = firstNameTextField.getText();
            String customerLastName = lastNameTextField.getText();
            String customerName = customerFirstName + " " + customerLastName;
            byte active = activeComboBox.getValue();

            int addressId = 1; // for loop creates a unique addressId by checking the address list for duplicate id number
            for(int i=0; i < Address.getAllAddresses().size(); i++) {
                if(Address.getAllAddresses().get(i).getAddressId() == addressId) {
                    addressId++;
                } else break;
            }

            String address1 = address1TextField.getText();
            String address2 = address2TextField.getText();
            String postalCode = postalCodeTextField.getText();
            String phone = phoneTextField.getText();

            int cityId = 1;
            boolean found = false;
            String cityName = cityTextField.getText();
            for(int i=0; i < City.getAllCities().size(); i++) {
                if(cityName.equals(City.getAllCities().get(i).getCity())) {
                    cityId = City.getAllCities().get(i).getCityId();
                    found = true;
                }
            }

            if(!found) {
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
            if(customerFirstName == null || customerLastName == null) {
                Alerts.errorCustomer(11);
                noErrors = false;
            }
            if(customerName.length() > 45) {
                Alerts.errorCustomer(2);
                noErrors = false;
            }
            if(address1.length() > 50) {
                Alerts.errorCustomer(4);
                noErrors = false;
            }
            if(address2.length() > 50) {
                Alerts.errorCustomer(5);
                noErrors = false;
            }
            if(postalCode.length() > 10) {
                Alerts.errorCustomer(3);
                noErrors = false;
            }
            if(phone.length() > 20) {
                Alerts.errorCustomer(12);
                noErrors = false;
            }
            if(cityName.length() > 50) {
                Alerts.errorCustomer(6);
                noErrors = false;
            }

            City addCity = new City(cityId, cityName, countryID, createDate, createdBy, lastUpdate, lastUpdateBy);
            Address addAddress = new Address(addressId, address1, address2, cityId, postalCode, phone, createDate, createdBy,
                    lastUpdate, lastUpdateBy);
            Customer addCustomer = new Customer(customerID, customerName, addressId, active, createDate, createdBy,
                    lastUpdate, lastUpdateBy);

            CityDaoImpl.addCity(addCity);
            AddressDaoImpl.addAddress(addAddress);
            CustomerDaoImpl.addCustomer(addCustomer);

            if(!noErrors) {
                Parent parent = FXMLLoader.load(getClass().getResource("allCustomers.fxml"));
                Scene scene = new Scene(parent);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            }

        } catch (NullPointerException e) {
            System.out.println("Null Pointer Ex: " + e.getMessage());
            if(countryCombo.getValue() == null) Alerts.errorCustomer(10);
            if(activeComboBox.getValue() == null) Alerts.errorCustomer(9);
        }
//        catch (SQLIntegrityConstraintViolationException e) {
//            System.out.println("SQLIntegrityConstraintViolationException" + e.getMessage() + e.getLocalizedMessage());
//        }
        catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
