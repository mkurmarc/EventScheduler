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
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import static java.time.ZoneId.systemDefault;
import static java.time.ZoneOffset.UTC;

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
            String createdBy = resultSet.getString("createdBy");
            String lastUpdateBy = resultSet.getString("lastUpdateBy");
            /*
            below converts start, end, createDate, lastUpdate DB timestamp to LocalDateTime to ZonedDateTime in UTC to
            ZonedDateTime in system default then back to LocalDateTime
            */
            LocalDateTime start = resultSet.getTimestamp("start").toLocalDateTime();
            ZonedDateTime startOrigin = start.atZone(UTC);
            ZonedDateTime startTarget = startOrigin.withZoneSameInstant(systemDefault());
            start = startTarget.toLocalDateTime();

            LocalDateTime end = resultSet.getTimestamp("end").toLocalDateTime();
            ZonedDateTime endOrigin = end.atZone(UTC);
            ZonedDateTime endTarget = endOrigin.withZoneSameInstant(systemDefault());
            end = endTarget.toLocalDateTime();

            LocalDateTime createDate = resultSet.getTimestamp("createDate").toLocalDateTime();
            ZonedDateTime createDateOrigin = createDate.atZone(UTC);
            ZonedDateTime createDateTarget = createDateOrigin.withZoneSameInstant(systemDefault());
            createDate = createDateTarget.toLocalDateTime();

            LocalDateTime lastUpdate = resultSet.getTimestamp("lastUpdate").toLocalDateTime();
            ZonedDateTime lastUpdateOrigin = lastUpdate.atZone(UTC);
            ZonedDateTime lastUpdateTarget = lastUpdateOrigin.withZoneSameInstant(systemDefault());
            lastUpdate = lastUpdateTarget.toLocalDateTime();
            // create each row into a country object, and then add it to the observable list
            Appointment appointmentObject = new Appointment(appointmentID, customerId, userId, title,
                description, location, contact, type, url, start, end, createDate, createdBy, lastUpdate,
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
            LocalDateTime start = resultSet.getTimestamp("start").toLocalDateTime();
            LocalDateTime end = resultSet.getTimestamp("end").toLocalDateTime();
            // getDate() retrieves date from db column. toLocalDate() converts it into LocalDate type
            LocalDateTime dateTime = resultSet.getTimestamp("createDate").toLocalDateTime();
            String createdBy = resultSet.getString("createdBy");
            LocalDateTime lastUpdate = resultSet.getTimestamp("lastUpdate").toLocalDateTime();
            String lastUpdateBy = resultSet.getString("lastUpdateBy");

            // create each row into a country object, and then add it to the observable list
            appointmentObject = new Appointment(appointmentID, customerId, userId, title,
                    description, location, contact, type, url, start, end, dateTime, createdBy, lastUpdate,
                    lastUpdateBy);
        }
        DBConnection.closeConnection(); // close DB connection
        return appointmentObject;
    }

    public void createAppointment(Appointment appointment) throws SQLException {
        Connection conn = DBConnection.startConnection(); // connect to DB

        String insertStatement = "INSERT INTO appointment(customerId, userId, title, description," +
                "location, contact, type, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy)" +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

        DBQuery.setPreparedStatement(conn, insertStatement); // creates preparedStatement

        PreparedStatement ps = DBQuery.getPreparedStatement();

        // Values for the insert statement are set below

        ps.setInt(1, appointment.getCustomerId());
        ps.setInt(2, appointment.getUserId());
        ps.setString(3, appointment.getTitle());
        ps.setString(4, appointment.getDescription());
        ps.setString(5, appointment.getLocation());
        ps.setString(6, appointment.getContact());
        ps.setString(7, appointment.getType());
        ps.setString(8, appointment.getUrl());
        ps.setTimestamp(9, Timestamp.valueOf(appointment.getStart()));
        ps.setTimestamp(10, Timestamp.valueOf(appointment.getEnd()));
        ps.setTimestamp(11, Timestamp.valueOf(appointment.getCreateDate()));
        ps.setString(12, appointment.getCreatedBy());
        ps.setTimestamp(13, Timestamp.valueOf(appointment.getLastUpdate()));
        ps.setString(14, appointment.getLastUpdateBy());

        ps.execute(); // execute PreparedStatement
        DBConnection.closeConnection(); // close DB connection
    }

    // Update or modify a single row of data from the database
    public static void updateAppointment(Appointment appointment) throws SQLException {
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
        ps.setTimestamp(7, Timestamp.valueOf(appointment.getStart()));
        ps.setTimestamp(8, Timestamp.valueOf(appointment.getEnd()));
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