package appointmentScheduler.DAO.Impl;

import appointmentScheduler.Model.Address;
import appointmentScheduler.Model.Appointment;
import appointmentScheduler.Model.Customer;
import appointmentScheduler.Utilities.DBConnection;
import appointmentScheduler.Utilities.DBQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class AddressDaoImpl {
    // Read all the data from the mySQL database
    public static ObservableList<Address> getAllAddresses() throws SQLException {
        Connection conn = DBConnection.startConnection(); // connect to DB
        ObservableList<Address> allAddressList = FXCollections.observableArrayList();

        String selectStatement = "SELECT * FROM address";

        DBQuery.setPreparedStatement(conn, selectStatement); // create PreparedStatement

        PreparedStatement ps =  DBQuery.getPreparedStatement();

        ps.execute(); // execute PreparedStatement

        ResultSet resultSet = ps.getResultSet();

        while (resultSet.next())
        {
            int addressId = resultSet.getInt("addressId");
            String address = resultSet.getString("address");
            String address2 = resultSet.getString("address2");
            int cityId = resultSet.getInt("cityId");
            String postalCode = resultSet.getString("postalCode");
            String phone = resultSet.getString("phone");
            LocalDateTime createDate = resultSet.getTimestamp("createDate").toLocalDateTime();
            String createdBy = resultSet.getString("createdBy");
            LocalDateTime lastUpdate = resultSet.getTimestamp("lastUpdate").toLocalDateTime();
            String lastUpdateBy = resultSet.getString("lastUpdateBy");

            Address addressObject = new Address(addressId, address, address2, cityId, postalCode, phone, createDate,
                    createdBy, lastUpdate, lastUpdateBy);

            allAddressList.add(addressObject);
        }
        DBConnection.closeConnection(); // close DB connection
        return allAddressList;
    }

    // Read or retrieve a single row of data from the mySQL database
    public static Address getAddress(int addressID) throws SQLException {
        Connection conn = DBConnection.startConnection(); // connect to DB

        Address addressObject = new Address();

        String selectStatement = "SELECT * FROM address WHERE addressId =?";

        DBQuery.setPreparedStatement(conn, selectStatement); // create PreparedStatement

        PreparedStatement ps =  DBQuery.getPreparedStatement();

        ps.setInt(1, addressID);

        ps.execute(); // execute PreparedStatement

        ResultSet resultSet = ps.getResultSet();

        while (resultSet.next())
        {
            int addressId = resultSet.getInt("addressId");
            String address = resultSet.getString("address");
            String address2 = resultSet.getString("address2");
            int cityId = resultSet.getInt("cityId");
            String postalCode = resultSet.getString("postalCode");
            String phone = resultSet.getString("phone");
            LocalDateTime createDate = resultSet.getTimestamp("createDate").toLocalDateTime();
            String createdBy = resultSet.getString("createdBy");
            LocalDateTime lastUpdate = resultSet.getTimestamp("lastUpdate").toLocalDateTime();
            String lastUpdateBy = resultSet.getString("lastUpdateBy");

            addressObject = new Address(addressId, address, address2, cityId, postalCode, phone, createDate,
                    createdBy, lastUpdate, lastUpdateBy);
        }
        DBConnection.closeConnection(); // close DB connection
        return addressObject;
    }

    public void createAddress(Address address) throws SQLException {
        Connection conn = DBConnection.startConnection(); // connect to DB

        String insertStatement = "INSERT INTO address (addressId, address, address2, cityId, postalCode, phone, " +
                "createDate, createdBy, lastUpdate, lastUpdateBy) VALUES(?,?,?,?,?,?,?,?,?,?);";

        DBQuery.setPreparedStatement(conn, insertStatement); // creates preparedStatement

        PreparedStatement ps = DBQuery.getPreparedStatement();

        // Values for the insert statement are set below
        ps.setInt(1, address.getAddressId());
        ps.setString(2, address.getAddress());
        ps.setString(3, address.getAddress2());
        ps.setInt(4, address.getCityId());
        ps.setString(5, address.getPostalCode());
        ps.setString(6, address.getPhone());
        ps.setTimestamp(7, Timestamp.valueOf(address.getCreateDate()));
        ps.setString(8, address.getCreatedBy());
        ps.setTimestamp(9, Timestamp.valueOf(address.getLastUpdate()));
        ps.setString(10, address.getLastUpdateBy());

        ps.execute(); // execute PreparedStatement
        DBConnection.closeConnection(); // close DB connection
    }

    public static void updateAddress(Address addressParameter) throws SQLException {
        Connection conn = DBConnection.startConnection(); // connect to DB

        String updateStatement = "UPDATE appointment SET address =?, address2 =?, cityId =?, postalCode =?," +
                "phone =?, createDate =?, createdBy =?, lastUpdate =?, lastUpdateBy =? WHERE appointmentId =?;";

        DBQuery.setPreparedStatement(conn, updateStatement); // creates preparedStatement
        PreparedStatement ps =  DBQuery.getPreparedStatement();

        // Values for the update statement are set below
        ps.setString(1, addressParameter.getAddress());
        ps.setString(2, addressParameter.getAddress2());
        ps.setInt(3, addressParameter.getCityId());
        ps.setString(4, addressParameter.getPostalCode());
        ps.setString(5, addressParameter.getPhone());
        ps.setTimestamp(6, Timestamp.valueOf(addressParameter.getCreateDate()));
        ps.setString(7, addressParameter.getCreatedBy());
        ps.setTimestamp(8, Timestamp.valueOf(addressParameter.getLastUpdate()));
        ps.setString(9, addressParameter.getLastUpdateBy());
        ps.setInt(10, addressParameter.getAddressId());

        ps.execute(); // execute PreparedStatement
        DBConnection.closeConnection(); // close DB connection
    }

    public void deleteAddress(Address addressArgument) throws SQLException {
        Connection conn = DBConnection.startConnection(); // connect to DB

        String deleteStatement = "DELETE FROM address WHERE addressId = ?;";

        DBQuery.setPreparedStatement(conn, deleteStatement); // create PreparedStatement

        PreparedStatement ps = DBQuery.getPreparedStatement();

        ps.setInt(1, addressArgument.getAddressId()); // Sets value for prepared statement

        ps.execute(); // execute PreparedStatement
        DBConnection.closeConnection(); // close DB connection

    }
}
