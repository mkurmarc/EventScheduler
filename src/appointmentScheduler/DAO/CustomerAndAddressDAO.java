package appointmentScheduler.DAO;

import appointmentScheduler.Model.CustomerAndAddress;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface CustomerAndAddressDAO {
    // For the customer table
    public ObservableList<CustomerAndAddress> getAllCustomers() throws SQLException;
    public CustomerAndAddress getCustomer(int customerId);
    public void addCustomer(CustomerAndAddress customerAndAddress);
    public void updateCustomer(CustomerAndAddress customerAndAddress);
    public void deleteCustomer(CustomerAndAddress customerAndAddress);

}
/*
    @AUTHOR
    Marc Rios
    ID:
*/