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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public void addCustomer(Customer customer) {

    }

    public void updateCustomer(Customer customer) {
        // update
    }

    public static  void deleteCustomer(Customer customer) throws SQLException {
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