package appointmentScheduler.Model;
/*
    @AUTHOR
    Marc Rios
    ID:
*/
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Country {
    // this list stores the data retrieved from the DB
    private static ObservableList<Country> allCountries = FXCollections.observableArrayList();

    private int countryId;
    private String country;
    private LocalDate createDate;
    private LocalTime createDateTime;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdateBy;

    public Country(int countryId, String country, LocalDate createDate, LocalTime createDateTime, String createdBy,
                   LocalDateTime lastUpdate, String lastUpdateBy) {
        this.countryId = countryId;
        this.country = country;
        this.createDate = createDate;
        this.createDateTime = createDateTime;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdateBy = lastUpdateBy;
    }

    public Country() {

    }

    public static ObservableList<Country> getAllCountries() {
        return allCountries;
    }

    public static void setAllCountries(ObservableList<Country> allCountries) {
        Country.allCountries = allCountries;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
