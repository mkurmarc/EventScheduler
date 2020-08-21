package appointmentScheduler.DAO;
/*
    @AUTHOR
    Marc Rios
    ID:
*/
import appointmentScheduler.Model.City;
import appointmentScheduler.Model.User;
import javafx.collections.ObservableList;

public interface CityDAO {
    public ObservableList<City> getAllCity();
    public City getCity(int contactID); // not sure on the parameter variable, maybe it is cascading through the tables via PK and FK?
    public void updateCity(City city);
    public void deleteCity(City city);
}
/*
    @AUTHOR
    Marc Rios
    ID:
*/