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

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class AppointmentDaoImpl implements AppointmentDAO {

    // Read all the data from the mySQL database
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
            LocalDateTime start = resultSet.getTimestamp("start").toLocalDateTime();
            LocalDateTime end = resultSet.getTimestamp("end").toLocalDateTime();
            // getDate() retrieves date from db column. toLocalDate() converts it into LocalDate type
            LocalDateTime dateTime = resultSet.getTimestamp("createDate").toLocalDateTime();
            String createdBy = resultSet.getString("createdBy");
            LocalDateTime lastUpdate = resultSet.getTimestamp("lastUpdate").toLocalDateTime();
            String lastUpdateBy = resultSet.getString("lastUpdateBy");

            // create each row into a country object, and then add it to the observable list
            Appointment appointmentObject = new Appointment(appointmentID, customerId, userId, title,
                description, location, contact, type, url, start, end, dateTime, createdBy, lastUpdate, lastUpdateBy);

            selectAllAppointments.add(appointmentObject); // add object to observable list
        }
        DBConnection.closeConnection(); // close DB connection
        return selectAllAppointments;
    }

    // Read or retrieve a single row of data from the mySQL database
    @Override
    public Appointment getAppointment(int appointmentId) throws SQLException {

        Connection conn = DBConnection.startConnection(); // connect to DB

        Appointment appointmentObject = new Appointment();

        String selectStatement = "SELECT * FROM appointment WHERE appointmentId =?";

        DBQuery.setPreparedStatement(conn, selectStatement);

        PreparedStatement preparedStatement = DBQuery.getPreparedStatement();

        preparedStatement.setInt(1, appointmentId); // Sets appointmentId using the argument for this method

        preparedStatement.execute(); // execute PreparedStatement

        ResultSet resultSet = preparedStatement.getResultSet();

        while (resultSet.next()) {
            int appointmentID = resultSet.getInt("appointmentId");
            int customerId = resultSet.getInt("customerId");
            int userId = resultSet.getInt("userId");
            String title = resultSet.getString("title");
            String description = resultSet.getString("description");
            String location = resultSet.getString("location");
            String contact = resultSet.getString("contact");
            String type = resultSet.getString("type");
            String url = resultSet.getString("url");
            LocalDateTime start = resultSet.getTimestamp("start").toLocalDateTime();
            LocalDateTime end = resultSet.getTimestamp("end").toLocalDateTime();
            // getDate() retrieves date from db column. toLocalDate() converts it into LocalDate type
            LocalDateTime dateTime = resultSet.getTimestamp("createDate").toLocalDateTime();

            String createdBy = resultSet.getString("createdBy");
            LocalDateTime lastUpdate = resultSet.getTimestamp("lastUpdate").toLocalDateTime();
            String lastUpdateBy = resultSet.getString("lastUpdateBy");

            // create each row into a country object, and then add it to the observable list
            appointmentObject = new Appointment(appointmentID, customerId, userId, title, description, location,
                    contact, type, url, start, end, dateTime, createdBy, lastUpdate, lastUpdateBy);
        }
        DBConnection.closeConnection(); // close DB connection
        return appointmentObject;
    }

    // Update or modify a single row of data from the database
    @Override
    public void updateAppointment(Appointment appointment) throws SQLException {
        Connection conn = DBConnection.startConnection(); // connect to DB

        String updateStatement = "UPDATE appointment SET title =?, description =?, location =?, contact =?, type =?, " +
                "url =?, start =?, end =?, createDate =?, createdBy =?, lastUpdate =?, lastUpdateBy =? " +
                "WHERE appointmentId =?";

        DBQuery.setPreparedStatement(conn, updateStatement);

        PreparedStatement preparedStatement =  DBQuery.getPreparedStatement();

     // Values for the update statement are set below
        preparedStatement.setString(1, appointment.getTitle());
        preparedStatement.setString(2, appointment.getDescription());
        preparedStatement.setString(3, appointment.getLocation());
        preparedStatement.setString(4, appointment.getContact());
        preparedStatement.setString(5, appointment.getType());
        preparedStatement.setString(6, appointment.getUrl());
        preparedStatement.setTimestamp(7, Timestamp.valueOf(appointment.getStart()));
        preparedStatement.setTimestamp(8, Timestamp.valueOf(appointment.getEnd()));
        preparedStatement.setTimestamp(9, Timestamp.valueOf(appointment.getCreateDate()));
        preparedStatement.setString(10, appointment.getCreatedBy());
        preparedStatement.setTimestamp(11, Timestamp.valueOf(appointment.getLastUpdate()));
        preparedStatement.setString(12, appointment.getLastUpdateBy());
        preparedStatement.setInt(13, appointment.getAppointmentID());

        preparedStatement.execute(); // execute PreparedStatement

        DBConnection.closeConnection(); // close DB connection
    }

    @Override
    public void deleteAppointment(Appointment appointment) throws SQLException {
        Connection conn = DBConnection.startConnection(); // connect to DB

        String deleteStatement = "DELETE FROM country WHERE country = ?"; // index #s of ?s from left to right = (1,2,3,4,...)

        DBQuery.setPreparedStatement(conn, deleteStatement); // create PreparedStatement

        PreparedStatement preparedStatement = DBQuery.getPreparedStatement();

        preparedStatement.setInt(1, appointment.getAppointmentID()); // Sets value for prepared statement

        preparedStatement.execute(); // execute PreparedStatement

        DBConnection.closeConnection(); // close DB connection
    }
}
/*
    @AUTHOR
    Marc Rios
    ID:
*/