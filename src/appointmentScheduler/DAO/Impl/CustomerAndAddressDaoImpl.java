package appointmentScheduler.DAO.Impl;
/*
    @AUTHOR
    Marc Rios
    ID:
*/
import appointmentScheduler.DAO.CustomerAndAddressDAO;
import appointmentScheduler.Model.CustomerAndAddress;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomerAndAddressDaoImpl implements CustomerAndAddressDAO {

    ObservableList<CustomerAndAddress> allCustomers = FXCollections.observableArrayList();

    @Override
    public ObservableList<CustomerAndAddress> getAllCustomers() {
        return null;
    }

    @Override
    public CustomerAndAddress getCustomer(int customerId) {
        return null;
    }

    @Override
    public void addCustomer(CustomerAndAddress customerAndAddress) {

    }

    @Override
    public void updateCustomer(CustomerAndAddress customerAndAddress) {
        // update
    }

    @Override
    public void deleteCustomer(CustomerAndAddress customerAndAddress) {
        // delete
    }

    @Override
    public CustomerAndAddress getAddress(int contactID) {
        return null;
    }

    @Override
    public void addAddress(CustomerAndAddress customerAndAddress) {

    }

    @Override
    public void updateAddress(CustomerAndAddress address) {

    }

    @Override
    public void deleteAddress(CustomerAndAddress address) {

    }
}
/*
    @AUTHOR
    Marc Rios
    ID:
*/