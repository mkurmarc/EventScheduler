package appointmentScheduler.DAO.Impl;
/*
    @AUTHOR
    Marc Rios
    ID:
*/
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

public class CustomerDaoImpl {

    public static ObservableList<Customer> getAllCustomers() throws SQLException {
        Connection conn = DBConnection.startConnection(); // connect to DB
        ObservableList<Customer> allCustomersList = FXCollections.observableArrayList();

        String selectStatement = "SELECT * FROM customer";

        DBQuery.setPreparedStatement(conn, selectStatement); // create PreparedStatement

        PreparedStatement preparedStatement =  DBQuery.getPreparedStatement();

        preparedStatement.execute(); // execute PreparedStatement

        ResultSet resultSet = preparedStatement.getResultSet();

        while (resultSet.next())
        {
            int customerId = resultSet.getInt("customerId");
            String customerName = resultSet.getString("customerName");
            int addressId = resultSet.getInt("addressId");
            byte active = resultSet.getByte("active");
            LocalDateTime createDate = resultSet.getTimestamp("createDate").toLocalDateTime();
            String createdBy = resultSet.getString("createdBy");
            LocalDateTime lastUpdate = resultSet.getTimestamp("lastUpdate").toLocalDateTime();
            String lastUpdateBy = resultSet.getString("lastUpdateBy");

            Customer customerObject = new Customer(customerId, customerName, addressId, active, createDate, createdBy,
                    lastUpdate, lastUpdateBy);

            allCustomersList.add(customerObject);
        }
        DBConnection.closeConnection(); // close DB connection
        return allCustomersList;
    }

    public static Customer getCustomer(int customerId) throws SQLException {
        Connection conn = DBConnection.startConnection(); // connect to DB

        Customer customerObject = new Customer();

        String selectStatement = "SELECT * FROM customer WHERE customerId =?;";

        DBQuery.setPreparedStatement(conn, selectStatement);

        PreparedStatement ps = DBQuery.getPreparedStatement();

        ps.setInt(1, customerId); // Sets customerId using the argument for this method

        ps.execute(); // execute PreparedStatement

        ResultSet resultSet = ps.getResultSet();

        while (resultSet.next()) {
            int customerID = resultSet.getInt("customerId");
            String customerName = resultSet.getString("customerName");
            int addressId = resultSet.getInt("addressId");
            byte active = resultSet.getByte("active");
            LocalDateTime createDate = resultSet.getTimestamp("createDate").toLocalDateTime();
            String createdBy = resultSet.getString("createdBy");
            LocalDateTime lastUpdate = resultSet.getTimestamp("lastUpdate").toLocalDateTime();
            String lastUpdateBy = resultSet.getString("lastUpdateBy");

            // create each row into a customer object, and then add it to the observable list
            customerObject = new Customer(customerID, customerName, addressId, active, createDate, createdBy,
                    lastUpdate, lastUpdateBy);
        }
        DBConnection.closeConnection(); // close DB connection
        return customerObject;
    }

    public static void addCustomer(Customer customer) throws SQLException {
        Connection conn = DBConnection.startConnection(); // connect to DB

        String addCustomer = "INSERT INTO customer(customerId, customerName, addressId, active, createDate, createdBy," +
                "lastUpdate, lastUpdateBy) VALUES(?,?,?,?,?,?,?,?);";

        DBQuery.setPreparedStatement(conn, addCustomer); // creates preparedStatement
        PreparedStatement ps =  DBQuery.getPreparedStatement();

        // Values for the addCustomer statement are set below
        ps.setInt(1, customer.getCustomerId());
        ps.setString(2, customer.getCustomerName());
        ps.setInt(3, customer.getAddressId());
        ps.setByte(4, customer.getActive());
        ps.setTimestamp(5, Timestamp.valueOf(customer.getCreateDate()));
        ps.setString(6, customer.getCreatedBy());
        ps.setTimestamp(7, Timestamp.valueOf(customer.getLastUpdate()));
        ps.setString(8, customer.getLastUpdateBy());

        ps.execute(); // execute PreparedStatement
        DBConnection.closeConnection(); // close DB connection
    }
/*
    @AUTHOR
    Marc Rios
    ID:
*/
    public static void updateCustomer(Customer customerParameter) throws SQLException {
        Connection conn = DBConnection.startConnection(); // connect to DB

        String updateStatement = "UPDATE customer SET customerName =?, addressId =?, active =?, createDate =?, " +
                "createdBy =?, lastUpdate =?, lastUpdateBy =? WHERE customerId =?;";

        DBQuery.setPreparedStatement(conn, updateStatement); // creates preparedStatement
        PreparedStatement ps =  DBQuery.getPreparedStatement();

        // Values for the update statement are set below
        ps.setString(1, customerParameter.getCustomerName());
        ps.setInt(2, customerParameter.getAddressId());
        ps.setByte(3, customerParameter.getActive());
        ps.setTimestamp(4, Timestamp.valueOf(customerParameter.getCreateDate()));
        ps.setString(5, customerParameter.getCreatedBy());
        ps.setTimestamp(6, Timestamp.valueOf(customerParameter.getLastUpdate()));
        ps.setString(7, customerParameter.getLastUpdateBy());
        ps.setInt(8, customerParameter.getCustomerId());

        ps.execute(); // execute PreparedStatement
        DBConnection.closeConnection(); // close DB connection
    }

    public static void deleteCustomer(Customer customer) throws SQLException {
        Connection conn = DBConnection.startConnection(); // connect to DB

        String deleteStatement = "DELETE FROM customer WHERE customerId = ?;";

        DBQuery.setPreparedStatement(conn, deleteStatement); // create PreparedStatement
        PreparedStatement ps = DBQuery.getPreparedStatement();

        ps.setInt(1, customer.getCustomerId());

        ps.execute(); // execute PreparedStatement
        DBConnection.closeConnection(); // close DB connection
    }
}
/*
    @AUTHOR
    Marc Rios
    ID:
*/