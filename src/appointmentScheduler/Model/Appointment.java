package appointmentScheduler.Model;
/*
    @AUTHOR
    Marc Rios
    ID:
*/
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Appointment {
    private ObservableList<Contact> associatedContacts = FXCollections.observableArrayList();

    private int appointmentID;
    private String firstName;
    private String lastName;
    private int areaCode;
    private int subscriberNumber;
    private int phoneNumber;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private int zipCode;
    private String appointmentNotes;

    public Appointment(ObservableList<Contact> associatedContacts, int appointmentID, String firstName, String lastName,
                       int areaCode, int subscriberNumber, int phoneNumber, String address1, String address2,
                       String city, String state, int zipCode, String appointmentNotes) {
        this.associatedContacts = associatedContacts;
        this.appointmentID = appointmentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.areaCode = areaCode;
        this.subscriberNumber = subscriberNumber;
        this.phoneNumber = phoneNumber;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.appointmentNotes = appointmentNotes;
    }

    public ObservableList<Contact> getAssociatedContacts() {
        return associatedContacts;
    }

    public void setAssociatedContacts(ObservableList<Contact> associatedContacts) {
        this.associatedContacts = associatedContacts;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(int areaCode) {
        this.areaCode = areaCode;
    }

    public int getSubscriberNumber() {
        return subscriberNumber;
    }

    public void setSubscriberNumber(int subscriberNumber) {
        this.subscriberNumber = subscriberNumber;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getAppointmentNotes() {
        return appointmentNotes;
    }

    public void setAppointmentNotes(String appointmentNotes) {
        this.appointmentNotes = appointmentNotes;
    }
}
/*
    @AUTHOR
    Marc Rios
    ID:
*/