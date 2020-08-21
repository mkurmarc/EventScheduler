package appointmentScheduler.DAO;

import appointmentScheduler.Model.Customer;
import javafx.collections.ObservableList;

public interface CustomerDAO {
    // For the customer table
    public ObservableList<Customer> getAllCustomers();
    public Customer getCustomer(int customerId);
    public void addCustomer(Customer customer);
    public void updateCustomer(Customer customer);
    public void deleteCustomer(Customer customer);

    // For the address table
    public Customer getAddress(int addressId); // not sure on the parameter variable, maybe it is cascading through the tables via PK and FK?
    public void addAddress(Customer customer);
    public void updateAddress(Customer customer);
    public void deleteAddress(Customer customer);

}
/*
    @AUTHOR
    Marc Rios
    ID:
*/