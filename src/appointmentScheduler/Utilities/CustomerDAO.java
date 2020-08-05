package appointmentScheduler.Utilities;

import appointmentScheduler.Model.Customer;
import javafx.collections.ObservableList;

import java.util.List;

public interface CustomerDAO {
    public ObservableList<Customer> getAllCustomers();
    public Customer getStudent(int contactID);
    public void updateCustomer(Customer customer);
    public void deleteCustomer(Customer customer);

}
