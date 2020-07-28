package appointmentScheduler.Model;

/*
    @AUTHOR
    Marc Rios
    ID:
*/

public abstract class Contact {

    private int contactID;
    private String firstName;
    private String lastName;
    private int areaCode;
    private int subscriberNumber;
    private int phoneNumber; // this is areaCode and subscriberNumber combined
    private String address1;
    private String address2;
    private String city;
    private String state;
    private int zipCode;


    // Constructor for superclass Contact
    public Contact(int contactID, String firstName, String lastName, int phoneNumber, String address1, String address2,
                   String city, String state, int zipCode) {
        this.contactID = contactID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    /*
 Method to keep ID generation automated
    public static int generatePartId() {
        int idToCompare = 1;
        for (int i = 0; i < Inventory.getAllParts().size(); i++) {
            if (idToCompare < Inventory.getAllParts().get(i).getIdPart()) {
                idToCompare = Inventory.getAllParts().get(i).getIdPart();
            }
        }
        idToCompare += 1;
        return idToCompare;
    }
*/

    public int getContactID() {
        return contactID;
    }

    public void setContactID(int contactID) {
        this.contactID = contactID;
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
}
/*
    @AUTHOR
    Marc Rios
    ID:
*/