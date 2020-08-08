package appointmentScheduler.DAO;

import appointmentScheduler.Model.Address;
import javafx.collections.ObservableList;

public interface AddressDAO {
    public ObservableList<Address> getAllAddress();
    public Address getAddress(int contactID); // not sure on the parameter variable, maybe it is cascading through the tables via PK and FK?
    public void updateAddress(Address address);
    public void deleteAddress(Address address);
}
