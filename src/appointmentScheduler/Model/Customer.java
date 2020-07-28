package appointmentScheduler.Model;
/*
    @AUTHOR
    Marc Rios
    ID:
*/
public class Customer extends Contact {

    private String notes;

    public Customer(int contactID, String firstName, String lastName, int phoneNumber, String address1, String address2,
                    String city, String state, int zipCode, String notes) {
        super(contactID, firstName, lastName, phoneNumber, address1, address2, city, state, zipCode);
        setNotes(notes);
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
/*
    @AUTHOR
    Marc Rios
    ID:
*/