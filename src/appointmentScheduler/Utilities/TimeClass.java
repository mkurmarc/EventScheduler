package appointmentScheduler.Utilities;

import appointmentScheduler.Model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Collections;

/*
    @AUTHOR
    Marc Rios
    ID:
*/
public class TimeClass { // delete this class???

    private static ObservableList<LocalTime> listOfTimes = FXCollections.observableArrayList();

    public static ObservableList<LocalTime> getListOfTimes() {
        return listOfTimes;
    }

    public static void setListOfTimes(ObservableList<LocalTime> listOfTimes) {
        TimeClass.listOfTimes = listOfTimes;
    }

}
