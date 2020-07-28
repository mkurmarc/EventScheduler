package appointmentScheduler.Model;
/*
    @AUTHOR
    Marc Rios
    ID:
*/
public class Partner extends Contact {

    private String companyName;

    public Partner(int contactID, String firstName, String lastName, int phoneNumber, String address1, String address2,
                   String city, String state, int zipCode, String companyName) {
        super(contactID, firstName, lastName, phoneNumber, address1, address2, city, state, zipCode);
        setCompanyName(companyName);
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
/*
    @AUTHOR
    Marc Rios
    ID:
*/