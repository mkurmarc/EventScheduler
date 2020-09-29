package appointmentScheduler.DAO.Impl;
/*
    @AUTHOR
    Marc Rios
    ID:
*/
import appointmentScheduler.Model.Customer;
import appointmentScheduler.Utilities.DBConnection;
import appointmentScheduler.Utilities.DBQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class CustomerDaoImpl {

    ObservableList<Customer> getAllCustomers = FXCollections.observableArrayList();

    public ObservableList<Customer> getAllCustomersAddress() throws SQLException {
        Connection conn = DBConnection.startConnection(); // connect to DB
        ObservableList<Customer> allCustomersList = FXCollections.observableArrayList();

        String selectStatement = "SELECT * FROM customers";

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

    public Customer getCustomer(int customerId) {
        return null;
    }

    public void addCustomer(Customer customerAndAddress) {

    }

    public void updateCustomer(Customer customerAndAddress) {
        // update
    }

    public void deleteCustomer(Customer customerAndAddress) {
        // delete
    }
}
/*
    @AUTHOR
    Marc Rios
    ID:
*/