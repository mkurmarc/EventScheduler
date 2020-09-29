package appointmentScheduler.DAO.Impl;
/*
    @AUTHOR
    Marc Rios
    ID:
*/
import appointmentScheduler.Model.CustomerAndAddress;
import appointmentScheduler.Utilities.DBConnection;
import appointmentScheduler.Utilities.DBQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class CustomerAndAddressDaoImpl {

    ObservableList<CustomerAndAddress> allCustomers = FXCollections.observableArrayList();

    public ObservableList<CustomerAndAddress> getAllCustomers() throws SQLException {
        ObservableList<CustomerAndAddress> selectAllCustomers = FXCollections.observableArrayList();

        Connection conn = DBConnection.startConnection(); // connect to DB

        String selectStatement = "SELECT address.address, address.address2, address.postalCode, address.phone," +
                                 "address.createDate, address.createdBy, address.lastUpdate, address.lastUpdateBy," +
                                 "customer.customerName, customer.active " +
                                 "FROM address" +
                                 "INNER JOIN customer ON customer.addressId = address.addressId" +
                                 "ORDER BY address.createDate ASC;";

        DBQuery.setPreparedStatement(conn, selectStatement); // create PreparedStatement

        PreparedStatement preparedStatement =  DBQuery.getPreparedStatement();

        preparedStatement.execute(); // execute PreparedStatement

        ResultSet resultSet = preparedStatement.getResultSet();

        while (resultSet.next())
        {
            int customerId = resultSet.getInt("customerId");
            String customerName = resultSet.getString("customerName");
            byte active = resultSet.getByte("active");
            int addressId = resultSet.getInt("addressId");
            String address = resultSet.getString("address");
            String address2 = resultSet.getString("address2");
            int cityId = resultSet.getInt("cityId");
            String postalCode = resultSet.getString("postalCode");
            String phone = resultSet.getString("phone");
            LocalDateTime dateTime = resultSet.getTimestamp("createDate").toLocalDateTime();
            String createdBy = resultSet.getString("createdBy");
            LocalDateTime lastUpdate = resultSet.getTimestamp("lastUpdate").toLocalDateTime();
            String lastUpdateBy = resultSet.getString("lastUpdateBy");


        }


        return null;
    }

    public CustomerAndAddress getCustomer(int customerId) {
        return null;
    }

    public void addCustomer(CustomerAndAddress customerAndAddress) {

    }

    public void updateCustomer(CustomerAndAddress customerAndAddress) {
        // update
    }

    public void deleteCustomer(CustomerAndAddress customerAndAddress) {
        // delete
    }
}
/*
    @AUTHOR
    Marc Rios
    ID:
*/