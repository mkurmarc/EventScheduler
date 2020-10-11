package appointmentScheduler.DAO.Impl;
/*
    @AUTHOR
    Marc Rios
    ID:
*/
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

public class CountryDaoImpl {

    public static ObservableList<Country> getAllCountry() throws SQLException {

        ObservableList<Country> selectAllCountries = FXCollections.observableArrayList();
        Connection conn = DBConnection.startConnection(); // connect to DB

        String selectStatement = "SELECT * FROM country"; // index #s of ?s from left to right = (1,2,3,4,...)
        DBQuery.setPreparedStatement(conn, selectStatement); // create PreparedStatement
        PreparedStatement preparedStatement =  DBQuery.getPreparedStatement();

        preparedStatement.execute(); // execute PreparedStatement
        ResultSet resultSet = preparedStatement.getResultSet();

        while (resultSet.next()) // while there is data in ResultSet the while loop continues
        {
            int countryId = resultSet.getInt("countryId");
            String countryName = resultSet.getString("country");
            // getDate() retrieves date from db column. toLocalDate() converts it into LocalDate type
            LocalDateTime createDate = resultSet.getTimestamp("createDate").toLocalDateTime();
            String createdBy = resultSet.getString("createdBy");
            LocalDateTime lastUpdate = resultSet.getTimestamp("lastUpdate").toLocalDateTime();
            String lastUpdateBy = resultSet.getString("lastUpdateBy");

            // create each row into a country object, and then add it to the observable list
            Country countryObject = new Country(countryId, countryName, createDate, createdBy, lastUpdate, lastUpdateBy);
            selectAllCountries.add(countryObject); // add object to observable list
        }
        DBConnection.closeConnection(); // close DB connection
        return selectAllCountries;
    }

    public static Country getCountry(int countryID) throws SQLException {

        Connection conn = DBConnection.startConnection(); // connect to DB

        Country selectedCountry = new Country();

        String selectStatement = "SELECT * FROM country WHERE countryId =?;";

        DBQuery.setPreparedStatement(conn, selectStatement);
        PreparedStatement ps = DBQuery.getPreparedStatement();

        ps.setInt(1,countryID);

        ps.execute();
        ResultSet resultSet = ps.getResultSet();

        while (resultSet.next())
        {
            int countryId = resultSet.getInt("countryId");
            String country = resultSet.getString("country");
            LocalDateTime createDate = resultSet.getTimestamp("createDate").toLocalDateTime();
            String createdBy = resultSet.getString("createdBy");
            LocalDateTime lastUpdate = resultSet.getTimestamp("lastUpdate").toLocalDateTime();
            String lastUpdateBy = resultSet.getString("lastUpdateBy");

            selectedCountry = new Country(countryID, country, createDate, createdBy, lastUpdate, lastUpdateBy);
        }
        DBConnection.closeConnection(); // close DB connection

        return selectedCountry;
    }
}
/*
    @AUTHOR
    Marc Rios
    ID:
*/