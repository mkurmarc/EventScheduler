package appointmentScheduler.View_Controller;

import appointmentScheduler.DAO.Impl.AppointmentDaoImpl;
import appointmentScheduler.DAO.Impl.CustomerDaoImpl;
import appointmentScheduler.Model.Appointment;
import appointmentScheduler.Model.Customer;
import appointmentScheduler.Utilities.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static appointmentScheduler.Utilities.Alerts.confirmationWindow;

public class allCustomersController implements Initializable {
    @FXML
    private TableView<Customer> customerTableView;

    @FXML
    private TableColumn<Customer, String> nameColumn;

    @FXML
    private TableColumn<Customer, Byte> activeColumn;

    @FXML
    private TableColumn<Customer, String> lastUpdateByColumn;

    @FXML
    private Button viewCustomerDetailsButton;

    @FXML
    private Button editCustomerButton;

    @FXML
    private Button addCustomerButton;

    @FXML
    private Button deleteCustomerButton;

    @FXML
    private Button backToDashboardButton;

    private static int indexOfSelectedCustomer;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Sets the customer table
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        activeColumn.setCellValueFactory(new PropertyValueFactory<>("active"));
        lastUpdateByColumn.setCellValueFactory(new PropertyValueFactory<>("lastUpdateBy"));

        try {
            Customer.setAllCustomers(CustomerDaoImpl.getAllCustomers());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        customerTableView.setItems(Customer.getAllCustomers());
    }

    public static int getIndexOfSelectedCustomer() {
        return indexOfSelectedCustomer;
    }

    @FXML
    void viewCustomerDetailsHandler(ActionEvent event) throws IOException {
        Customer viewCustomer = customerTableView.getSelectionModel().getSelectedItem();
        if (viewCustomer != null) {
            indexOfSelectedCustomer = Customer.getAllCustomers().indexOf(viewCustomer);

            Stage stage;
            Parent root;
            stage = (Stage) viewCustomerDetailsButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("customerInformation.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else {
            Alerts.selectionError(5);
        }
    }

    @FXML
    void addCustomerButtonHandler(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) addCustomerButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addCustomer.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void backToDashboardHandler(ActionEvent event) throws IOException {
        if (confirmationWindow(2)) {
            Stage stage;
            Parent root;
            stage = (Stage) backToDashboardButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void deleteCustomerButtonHandler(ActionEvent event) {
        Customer deleteCustomer = customerTableView.getSelectionModel().getSelectedItem();
        if (deleteCustomer != null) {
            if (Alerts.confirmationWindow(4)) {
                int customerID = deleteCustomer.getCustomerId();
                try {
                    AppointmentDaoImpl.deleteAppointmentByID(customerID);
                    CustomerDaoImpl.deleteCustomer(deleteCustomer);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Customer.getAllCustomers().remove(deleteCustomer);
                customerTableView.setItems(Customer.getAllCustomers());
            }
        }
        else {
            Alerts.selectionError(6);
        }
    }

    @FXML
    void editCustomerButtonHandler(ActionEvent event) throws IOException {
        Customer editCustomer = customerTableView.getSelectionModel().getSelectedItem();
        if (editCustomer != null) {
            indexOfSelectedCustomer = Customer.getAllCustomers().indexOf(editCustomer);

            Stage stage;
            Parent root;
            stage = (Stage) editCustomerButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("editCustomer.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else {
            Alerts.selectionError(7);
        }
    }
}
