package appointmentScheduler.DAO;

import appointmentScheduler.Model.Appointment;
import javafx.collections.ObservableList;

public interface AppointmentDAO {
    public ObservableList<Appointment> getAllAppointment();
    public Appointment getAppointment(int contactID); // not sure on the parameter variable, maybe it is cascading through the tables via PK and FK?
    public void updateAppointment(Appointment appointment);
    public void deleteAppointment(Appointment appointment);
}
