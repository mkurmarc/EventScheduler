package appointmentScheduler.DAO;
/*
    @AUTHOR
    Marc Rios
    ID:
*/
import appointmentScheduler.Model.Appointment;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface AppointmentDAO {
    public ObservableList<Appointment> getAllAppointment() throws SQLException;
    public Appointment getAppointment(int appointmentId) throws SQLException; // not sure on the parameter variable, maybe it is cascading through the tables via PK and FK?
    public void updateAppointment(Appointment appointment) throws SQLException;
    public void deleteAppointment(Appointment appointment) throws SQLException;
}
/*
    @AUTHOR
    Marc Rios
    ID:
*/