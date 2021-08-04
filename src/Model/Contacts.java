package Model;

/**
 * This contacts Class allows the program to create and modify Contacts objects.
 */
public class Contacts {

    private int contactId;
    private String contactName;
    private String contactEmail;

    /**
     * Default constructor for the Contacts class.
     * @param contactId int
     * @param contactName String
     * @param contactEmail String
     */
    public Contacts(int contactId, String contactName, String contactEmail) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }

    /**
     * Accessor (getter) methods gets and returns contactId
     * @return contactId
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * Mutator (setter) method sets the contactId
     * @param contactId this.contactId
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * Accessor (getter) methods gets and returns contactName
     * @return contactName
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Mutator (setter) method sets the contactName
     * @param contactName this.contactName
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * Accessor (getter) methods gets and returns contactEmail
     * @return contactEmail
     */
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     * Mutator (setter) method sets the contactEmail
     * @param contactEmail this.contactEmail
     */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    /**
     * This Override method that is needed to display the name of the contact on the ComboBox instead of the random
     * object reference.
     */
    @Override
    public String toString() {
        return contactName;
    }
}
