package appointmentScheduler.DAO.Impl;
/*
    @AUTHOR
    Marc Rios
    ID:
*/
import appointmentScheduler.DAO.CustomerDAO;
import appointmentScheduler.Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomerDaoImpl implements CustomerDAO {

    ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

    @Override
    public ObservableList<Customer> getAllCustomers() {
        return null;
    }

    @Override
    public Customer getCustomer(int customerId) {
        return null;
    }

    @Override
    public void addCustomer(Customer customer) {

    }

    @Override
    public void updateCustomer(Customer customer) {
        // update
    }

    @Override
    public void deleteCustomer(Customer customer) {
        // delete
    }

    @Override
    public Customer getAddress(int contactID) {
        return null;
    }

    @Override
    public void addAddress(Customer customer) {

    }

    @Override
    public void updateAddress(Customer address) {

    }

    @Override
    public void deleteAddress(Customer address) {

    }
}
/*
    @AUTHOR
    Marc Rios
    ID:
*/