package appointmentScheduler.Model;
/*
    @AUTHOR
    Marc Rios
    ID:
*/
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Appointment {
    private ObservableList<Customer> associatedContacts = FXCollections.observableArrayList();

    private int appointmentID;
    private int customerId;
    private int userId;
    private String title;
    private String description;
    private String location;
    private String contact;
    private String type;
    private String url;
    private String start;
    private String end;
    private String createDate;
    private String createdBy;
    private String lastUpdate;
    private String lastUpdatedBy;

    public Appointment(ObservableList<Customer> associatedContacts, int appointmentID, int customerId, int userId,
                       String title, String description, String location, String contact, String type, String url,
                       String start, String end, String createDate, String createdBy, String lastUpdate,
                       String lastUpdatedBy) {
        this.associatedContacts = associatedContacts;
        this.appointmentID = appointmentID;
        this.customerId = customerId;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.contact = contact;
        this.type = type;
        this.url = url;
        this.start = start;
        this.end = end;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public ObservableList<Customer> getAssociatedContacts() {
        return associatedContacts;
    }

    public void setAssociatedContacts(ObservableList<Customer> associatedContacts) {
        this.associatedContacts = associatedContacts;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }
}
/*
    @AUTHOR
    Marc Rios
    ID:
*/