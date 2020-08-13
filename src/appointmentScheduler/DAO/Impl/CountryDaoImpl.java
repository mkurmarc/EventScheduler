package appointmentScheduler.DAO.Impl;

import appointmentScheduler.DAO.CountryDAO;
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

public class CountryDaoImpl implements CountryDAO {
    @Override
    public ObservableList<Country> getAllCountry() throws SQLException {

        ObservableList<Country> selectAllCountries = FXCollections.observableArrayList();
        Connection conn = DBConnection.startConnection(); // connect to DB

        String selectStatement = "SELECT * FROM country"; // index #s of ?s from left to right = (1,2,3,4,...)
        DBQuery.setPreparedStatement(conn, selectStatement); // create PreparedStatement
        PreparedStatement ps =  DBQuery.getPreparedStatement();

        ps.execute(); // execute PreparedStatement
        ResultSet rs = ps.getResultSet();

        while (rs.next()) // while there is data in ResultSet the while loop continues
        {
            int countryId = rs.getInt("countryId");
            String countryName = rs.getString("country");
            // getDate() retrieves date from db column. toLocalDate() converts it into LocalDate type
            LocalDate date = rs.getDate("createDate").toLocalDate();
            LocalTime time = rs.getTime("createDate").toLocalTime();
            String createdBy = rs.getString("createdBy");
            LocalDateTime lastUpdate = rs.getTimestamp("lastUpdate").toLocalDateTime();
            String lastUpdateBy = rs.getString("lastUpdateBy");

            // create each row into a country object, and then add it to the observable list
            Country countryObject = new Country(countryId, countryName, date, time, createdBy, lastUpdate, lastUpdateBy);
            selectAllCountries.add(countryObject); // add object to observable list
        }
        DBConnection.closeConnection(); // close DB connection
        return selectAllCountries;
    }

    @Override
    public Country getCountry(int contactID) {
        return null;
/*
        Connection conn = DBConnection.startConnection(); // connect to DB

        String selectStatement = "SELECT  FROM country";
        
        DBConnection.closeConnection(); // close DB connection
*/
    }

    @Override
    public void updateCountry(Country country) {

    }

    @Override
    public void deleteCountry(Country country) {

    }
}
