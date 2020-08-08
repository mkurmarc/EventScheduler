package appointmentScheduler.DAO;

import appointmentScheduler.Model.Customer;
import appointmentScheduler.Model.User;
import javafx.collections.ObservableList;

public interface UserDAO {
    public ObservableList<User> getAllUsers();
    public User getUser(int contactID); // not sure on the parameter variable, maybe it is cascading through the tables via PK and FK?
    public void updateUser(User user);
    public void deleteUser(User user);
}
