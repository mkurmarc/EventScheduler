package appointmentScheduler.View_Controller;

import appointmentScheduler.Model.Country;
import appointmentScheduler.Model.Customer;
import appointmentScheduler.Model.User;
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
    private ComboBox<Customer> activeComboBox;

    @FXML
    private Button saveAddCustomerButton;

    @FXML
    private Button cancelAddCustomerButton;

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

        int customerID = 1;
        String customerFirstName = firstNameTextField.getText();
        String customerLastName = lastNameTextField.getText();
        String customerName = customerFirstName + " " + customerLastName;
        byte active = activeComboBox.getValue().getActive();

        int addressId = 123; // make auto increment function and place here
        String address1 = address1TextField.getText();
        String address2 = address2TextField.getText();
        String postalCode = postalCodeTextField.getText();
        String phone = phoneTextField.getText();

        int cityId = 45;
        String cityName = cityTextField.getText();

        int countryID = 1;
        String countryName = countryCombo.getValue().getCountry();

        // gets the last four parameters for all object creation in this handler
        LocalDateTime createDate = LocalDateTime.now();
        String createdBy = User.getUserList().get(0).getUserName();
        LocalDateTime lastUpdate = LocalDateTime.now();
        String lastUpdateBy = User.getUserList().get(0).getUserName();

        /*
        Customer(customerId, customerName, addressId, active, String createDate, String createdBy,
                String lastUpdate, lastUpdatedBy, address, address2, cityId,
        String postalCode, String phone)

         */
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
