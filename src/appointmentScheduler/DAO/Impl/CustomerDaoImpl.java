package appointmentScheduler.DAO.Impl;

import appointmentScheduler.DAO.CustomerDAO;
import appointmentScheduler.Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomerDaoImpl implements CustomerDAO {

    ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

    public CustomerDaoImpl() {
        //Customer customer1 = new Customer()
    }

    @Override
    public ObservableList<Customer> getAllCustomers() {
        return null;
    }

    @Override
    public Customer getStudent(int contactID) {
        return null;
    }

    @Override
    public void updateCustomer(Customer customer) {
        // update
    }

    @Override
    public void deleteCustomer(Customer customer) {
        // delete
    }
}
