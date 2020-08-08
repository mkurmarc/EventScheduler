package appointmentScheduler.DAO;

import appointmentScheduler.Model.Country;
import appointmentScheduler.Model.User;
import javafx.collections.ObservableList;

public interface CountryDAO {
    public ObservableList<Country> getAllCountry();
    public Country getCountry(int contactID); // not sure on the parameter variable, maybe it is cascading through the tables via PK and FK?
    public void updateCountry(Country country);
    public void deleteCountry(Country country);
}
