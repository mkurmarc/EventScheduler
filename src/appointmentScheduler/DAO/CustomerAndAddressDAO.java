package appointmentScheduler.DAO;

import appointmentScheduler.Model.CustomerAndAddress;
import javafx.collections.ObservableList;

public interface CustomerAndAddressDAO {
    // For the customer table
    public ObservableList<CustomerAndAddress> getAllCustomers();
    public CustomerAndAddress getCustomer(int customerId);
    public void addCustomer(CustomerAndAddress customerAndAddress);
    public void updateCustomer(CustomerAndAddress customerAndAddress);
    public void deleteCustomer(CustomerAndAddress customerAndAddress);

    // For the address table
    public CustomerAndAddress getAddress(int addressId); // not sure on the parameter variable, maybe it is cascading through the tables via PK and FK?
    public void addAddress(CustomerAndAddress customerAndAddress);
    public void updateAddress(CustomerAndAddress customerAndAddress);
    public void deleteAddress(CustomerAndAddress customerAndAddress);

}
/*
    @AUTHOR
    Marc Rios
    ID:
*/