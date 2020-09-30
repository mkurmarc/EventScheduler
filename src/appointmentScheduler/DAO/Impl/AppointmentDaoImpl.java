package appointmentScheduler.DAO.Impl;
/*
    @AUTHOR
    Marc Rios
    ID:
*/
import appointmentScheduler.Model.Appointment;
import appointmentScheduler.Utilities.DBConnection;
import appointmentScheduler.Utilities.DBQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AppointmentDaoImpl {


    // Read all the data from the mySQL database
    public static ObservableList<Appointment> getAllAppointments() throws SQLException {
        Connection conn = DBConnection.startConnection(); // connect to DB
        ObservableList<Appointment> selectAllAppointments = FXCollections.observableArrayList();

        String selectStatement = "SELECT * FROM appointment";

        DBQuery.setPreparedStatement(conn, selectStatement); // create PreparedStatement

        PreparedStatement ps =  DBQuery.getPreparedStatement();

        ps.execute(); // execute PreparedStatement

        ResultSet resultSet = ps.getResultSet();

//        DateTimeFormatter df = DateTimeFormatter.ofPattern("MM-dd-YYYY");
//        DateTimeFormatter tf = DateTimeFormatter.ofPattern("h:mm a");

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
            LocalDate startDate = resultSet.getTimestamp("start").toLocalDateTime().toLocalDate();
            LocalTime startTime = resultSet.getTimestamp("start").toLocalDateTime().toLocalTime();
            LocalTime end = resultSet.getTimestamp("end").toLocalDateTime().toLocalTime();
            // getDate() retrieves date from db column. toLocalDate() converts it into LocalDate type
            LocalDateTime dateTime = resultSet.getTimestamp("createDate").toLocalDateTime();
            String createdBy = resultSet.getString("createdBy");
            LocalDateTime lastUpdate = resultSet.getTimestamp("lastUpdate").toLocalDateTime();
            String lastUpdateBy = resultSet.getString("lastUpdateBy");

            // create each row into a country object, and then add it to the observable list
            Appointment appointmentObject = new Appointment(appointmentID, customerId, userId, title,
                description, location, contact, type, url, startDate, startTime, end, dateTime, createdBy, lastUpdate,
                    lastUpdateBy);

            selectAllAppointments.add(appointmentObject); // add object to observable list
        }
        DBConnection.closeConnection(); // close DB connection
        return selectAllAppointments; // returns the observable list
    }

    // Read or retrieve a single row of data from the mySQL database
    public Appointment getAppointment(int appointmentId) throws SQLException {
        Connection conn = DBConnection.startConnection(); // connect to DB

        Appointment appointmentObject = new Appointment();

        String selectStatement = "SELECT * FROM appointment WHERE appointmentId =?;";

        DBQuery.setPreparedStatement(conn, selectStatement);

        PreparedStatement ps = DBQuery.getPreparedStatement();

        ps.setInt(1, appointmentId); // Sets appointmentId using the argument for this method

        ps.execute(); // execute PreparedStatement

        ResultSet resultSet = ps.getResultSet();

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
            LocalDate startDate = resultSet.getTimestamp("start").toLocalDateTime().toLocalDate();
            LocalTime startTime = resultSet.getTimestamp("start").toLocalDateTime().toLocalTime();
            LocalTime end = resultSet.getTimestamp("end").toLocalDateTime().toLocalTime();
            // getDate() retrieves date from db column. toLocalDate() converts it into LocalDate type
            LocalDateTime dateTime = resultSet.getTimestamp("createDate").toLocalDateTime();
            String createdBy = resultSet.getString("createdBy");
            LocalDateTime lastUpdate = resultSet.getTimestamp("lastUpdate").toLocalDateTime();
            String lastUpdateBy = resultSet.getString("lastUpdateBy");

            // create each row into a country object, and then add it to the observable list
            appointmentObject = new Appointment(appointmentID, customerId, userId, title,
                    description, location, contact, type, url, startDate, startTime, end, dateTime, createdBy, lastUpdate,
                    lastUpdateBy);
        }
        DBConnection.closeConnection(); // close DB connection
        return appointmentObject;
    }

    public void createAppointment(Appointment appointment) throws SQLException {
        Connection conn = DBConnection.startConnection(); // connect to DB

        String insertStatement = "INSERT INTO appointment(appointmentId, customerId, userId, title, description," +
                "location, contact, type, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy)" +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

        DBQuery.setPreparedStatement(conn, insertStatement); // creates preparedStatement

        PreparedStatement ps = DBQuery.getPreparedStatement();

        // Values for the insert statement are set below
        ps.setInt(1, appointment.getAppointmentId());
        ps.setInt(2, appointment.getCustomerId());
        ps.setInt(3, appointment.getUserId());
        ps.setString(4, appointment.getTitle());
        ps.setString(5, appointment.getDescription());
        ps.setString(6, appointment.getLocation());
        ps.setString(7, appointment.getContact());
        ps.setString(8, appointment.getType());
        ps.setString(9, appointment.getUrl());
        ps.setTimestamp(10, Timestamp.valueOf(appointment.getStartDate().atTime(appointment.getStartTime())));
        ps.setTimestamp(11, Timestamp.valueOf(appointment.getEnd().atDate(LocalDate.now())));
        ps.setTimestamp(12, Timestamp.valueOf(appointment.getCreateDate()));
        ps.setString(13, appointment.getCreatedBy());
        ps.setTimestamp(14, Timestamp.valueOf(appointment.getLastUpdate()));
        ps.setString(15, appointment.getLastUpdateBy());

        ps.execute(); // execute PreparedStatement
        DBConnection.closeConnection(); // close DB connection
    }

    // Update or modify a single row of data from the database
    public void updateAppointment(Appointment appointment) throws SQLException {
        Connection conn = DBConnection.startConnection(); // connect to DB

        String updateStatement = "UPDATE appointment SET title =?, description =?, location =?, contact =?, type =?, " +
                "url =?, start =?, end =?, createDate =?, createdBy =?, lastUpdate =?, lastUpdateBy =? " +
                "WHERE appointmentId =?;";

        DBQuery.setPreparedStatement(conn, updateStatement); // creates preparedStatement
        PreparedStatement ps =  DBQuery.getPreparedStatement();

     // Values for the update statement are set below
        ps.setString(1, appointment.getTitle());
        ps.setString(2, appointment.getDescription());
        ps.setString(3, appointment.getLocation());
        ps.setString(4, appointment.getContact());
        ps.setString(5, appointment.getType());
        ps.setString(6, appointment.getUrl());
        ps.setTimestamp(7, Timestamp.valueOf(appointment.getStartDate().atTime(appointment.getStartTime())));
        ps.setTimestamp(8, Timestamp.valueOf(appointment.getEnd().atDate(LocalDate.now())));
        ps.setTimestamp(9, Timestamp.valueOf(appointment.getCreateDate()));
        ps.setString(10, appointment.getCreatedBy());
        ps.setTimestamp(11, Timestamp.valueOf(appointment.getLastUpdate()));
        ps.setString(12, appointment.getLastUpdateBy());
        ps.setInt(13, appointment.getAppointmentId());

        ps.execute(); // execute PreparedStatement
        DBConnection.closeConnection(); // close DB connection
    }

    public static void deleteAppointment(Appointment appointment) throws SQLException {
        Connection conn = DBConnection.startConnection(); // connect to DB

        String deleteStatement = "DELETE FROM appointment WHERE appointmentId = ?;";

        DBQuery.setPreparedStatement(conn, deleteStatement); // create PreparedStatement

        PreparedStatement ps = DBQuery.getPreparedStatement();

        ps.setInt(1, appointment.getAppointmentId()); // Sets value for prepared statement

        ps.execute(); // execute PreparedStatement
        DBConnection.closeConnection(); // close DB connection
    }
}
/*
    @AUTHOR
    Marc Rios
    ID:
*/