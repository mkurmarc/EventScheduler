package appointmentScheduler.DAO.Impl;
/*
    @AUTHOR
    Marc Rios
    ID:
*/
import appointmentScheduler.Model.Address;
import appointmentScheduler.Model.City;
import appointmentScheduler.Model.Customer;
import appointmentScheduler.Utilities.DBConnection;
import appointmentScheduler.Utilities.DBQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDateTime;

public class CityDaoImpl {

    public static ObservableList<City> getAllCities() throws SQLException {
        Connection conn = DBConnection.startConnection(); // connect to DB
        ObservableList<City> allCitiesList = FXCollections.observableArrayList();

        String selectStatement = "SELECT * FROM city";

        DBQuery.setPreparedStatement(conn, selectStatement); // create PreparedStatement

        PreparedStatement ps =  DBQuery.getPreparedStatement();

        ps.execute(); // execute PreparedStatement

        ResultSet resultSet = ps.getResultSet();

        while (resultSet.next())
        {
            int cityId = resultSet.getInt("cityId");
            String city = resultSet.getString("city");
            int countryId = resultSet.getInt("countryId");
            LocalDateTime createDate = resultSet.getTimestamp("createDate").toLocalDateTime();
            String createdBy = resultSet.getString("createdBy");
            LocalDateTime lastUpdate = resultSet.getTimestamp("lastUpdate").toLocalDateTime();
            String lastUpdateBy = resultSet.getString("lastUpdateBy");

            City cityObject = new City(cityId, city, countryId, createDate, createdBy, lastUpdate, lastUpdateBy);

            allCitiesList.add(cityObject);
        }
        DBConnection.closeConnection(); // close DB connection
        return allCitiesList;
    }

    public static City getCity(int cityId) throws SQLException {
        Connection conn = DBConnection.startConnection(); // connect to DB

        City cityObj = new City();

        String selectStatement = "SELECT * FROM city WHERE cityId =?";

        DBQuery.setPreparedStatement(conn, selectStatement); // create PreparedStatement

        PreparedStatement ps =  DBQuery.getPreparedStatement();

        ps.setInt(1, cityId);

        ps.execute(); // execute PreparedStatement

        ResultSet resultSet = ps.getResultSet();

        while (resultSet.next())
        {
            int cityID = resultSet.getInt("cityId");
            String city = resultSet.getString("city");
            int countryId = resultSet.getInt("countryId");
            LocalDateTime createDate = resultSet.getTimestamp("createDate").toLocalDateTime();
            String createdBy = resultSet.getString("createdBy");
            LocalDateTime lastUpdate = resultSet.getTimestamp("lastUpdate").toLocalDateTime();
            String lastUpdateBy = resultSet.getString("lastUpdateBy");

            cityObj = new City(cityID, city, countryId, createDate, createdBy, lastUpdate, lastUpdateBy);
        }
        DBConnection.closeConnection(); // close DB connection
        return cityObj;
    }

    public static void updateCity(City city) throws SQLException {
        Connection conn = DBConnection.startConnection(); // connect to DB

        String updateStatement = "UPDATE city SET city =?, countryId =?, createDate =?, createdBy =?, " +
                "lastUpdate =?, lastUpdateBy =? WHERE cityId =?;";

        DBQuery.setPreparedStatement(conn, updateStatement); // creates preparedStatement
        PreparedStatement ps =  DBQuery.getPreparedStatement();

        // Values for the update statement are set below
        ps.setString(1, city.getCity());
        ps.setInt(2, city.getCountryId());
        ps.setTimestamp(3, Timestamp.valueOf(city.getCreateDate()));
        ps.setString(4, city.getCreatedBy());
        ps.setTimestamp(5, Timestamp.valueOf(city.getLastUpdate()));
        ps.setString(6, city.getLastUpdateBy());
        ps.setInt(7, city.getCityId());

        ps.execute(); // execute PreparedStatement
        DBConnection.closeConnection(); // close DB connection
    }

    public static void addCity(City city) throws SQLException{
        Connection conn = DBConnection.startConnection(); // connect to DB

        String addCity = "INSERT INTO city(cityId, city, countryId, createDate, createdBy," +
                "lastUpdate, lastUpdateBy) VALUES(?,?,?,?,?,?,?);";

        DBQuery.setPreparedStatement(conn, addCity); // creates preparedStatement
        PreparedStatement ps =  DBQuery.getPreparedStatement();

        // Values for the add city statement are set below
        ps.setInt(1, city.getCityId());
        ps.setString(2, city.getCity());
        ps.setInt(3, city.getCountryId());
        ps.setTimestamp(4, Timestamp.valueOf(city.getCreateDate()));
        ps.setString(5, city.getCreatedBy());
        ps.setTimestamp(6, Timestamp.valueOf(city.getLastUpdate()));
        ps.setString(7, city.getLastUpdateBy());

        ps.execute(); // execute PreparedStatement
        DBConnection.closeConnection(); // close DB connection
    }

    public void deleteCity(City city) {
        // add if needed
    }
}
/*
    @AUTHOR
    Marc Rios
    ID:
*/