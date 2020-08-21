package appointmentScheduler.DAO;

import appointmentScheduler.Model.Country;
import appointmentScheduler.Model.User;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface CountryDAO {
    public ObservableList<Country> getAllCountry() throws SQLException;
    public Country getCountry(int countryID) throws SQLException; // not sure on the parameter variable, maybe it is cascading through the tables via PK and FK?
    public void insertCountry(Country country);
    public void updateCountry(Country country);
    public void deleteCountry(Country country);
}
/*
    @AUTHOR
    Marc Rios
    ID:
*/