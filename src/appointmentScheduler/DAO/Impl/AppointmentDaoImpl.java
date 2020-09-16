package appointmentScheduler.DAO.Impl;
/*
    @AUTHOR
    Marc Rios
    ID:
*/
import appointmentScheduler.DAO.AppointmentDAO;
import appointmentScheduler.Model.Appointment;
import appointmentScheduler.Model.Country;
import appointmentScheduler.Utilities.DBConnection;
import appointmentScheduler.Utilities.DBQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class AppointmentDaoImpl implements AppointmentDAO {
    @Override
    public ObservableList<Appointment> getAllAppointment() throws SQLException {

        ObservableList<Appointment> selectAllAppointments = FXCollections.observableArrayList();
        Connection conn = DBConnection.startConnection(); // connect to DB

        String selectStatement = "SELECT * FROM appointment";
        DBQuery.setPreparedStatement(conn, selectStatement); // create PreparedStatement
        PreparedStatement preparedStatement =  DBQuery.getPreparedStatement();
        preparedStatement.execute(); // execute PreparedStatement
        ResultSet resultSet = preparedStatement.getResultSet();

        while (resultSet.next()) // while there is data in ResultSet the while loop continues
        {
            int appointmentID = resultSet.getInt("appointmentId");
            int customerId = resultSet.getInt("customerId");
            int userId = resultSet.getInt("userId");
            String title = resultSet.getString("title");
            String description = resultSet.getString("description");
            String location = resultSet.getString("location");
            String contact = resultSet.getString("contact");
            String type = resultSet.getString("type");
            String url = resultSet.getString("url");
            String start = resultSet.getString("start");
            String end = resultSet.getString("end");
            // getDate() retrieves date from db column. toLocalDate() converts it into LocalDate type
            LocalDate date = resultSet.getDate("createDate").toLocalDate();
            LocalTime time = resultSet.getTime("createDate").toLocalTime();
            String createdBy = resultSet.getString("createdBy");
            LocalDateTime lastUpdate = resultSet.getTimestamp("lastUpdate").toLocalDateTime();
            String lastUpdateBy = resultSet.getString("lastUpdateBy");

            // create each row into a country object, and then add it to the observable list
            Appointment appointmentObject = new Appointment(appointmentID, customerId, userId, title,
                description, location, contact, type, url, start, end, date, time, createdBy, lastUpdate, lastUpdateBy);

            selectAllAppointments.add(appointmentObject); // add object to observable list
        }
        DBConnection.closeConnection(); // close DB connection
        return selectAllAppointments;
    }

    @Override
    public Appointment getAppointment(int contactID) {
        return null;
    }

    @Override
    public void updateAppointment(Appointment appointment) {

    }

    @Override
    public void deleteAppointment(Appointment appointment) {

    }
}
/*
    @AUTHOR
    Marc Rios
    ID:
*/