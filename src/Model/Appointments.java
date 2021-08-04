package Model;

import java.time.LocalDateTime;

/**
 * This public class Appointments allows the program to create and modify the Appointments objects.
 * @author Krystal Lee
 * @version 1.0
 * @since Summer 2021
 */
public class Appointments {
    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int customerId;
    private String customerName;
    private int userId;
    private String userName;
    private int contactId;
    private String contactName;

    // added for the MonthTypeReportScreen
    private String month;
    private int occurrences;

    /**
     * Default overloaded constructor for number of appointments by type and month report.
     * @param month String
     * @param type String
     * @param occurrences int
     */
    public Appointments(String month, String type, int occurrences) {
        this.month = month;
        this.type = type;
        this.occurrences = occurrences;
    }

    /**
     * Default overloaded constructor for contact schedule report.
     * @param appointmentId int
     * @param title String
     * @param description String
     * @param type String
     * @param startTime LocalDateTime
     * @param endTime LocalDateTime
     * @param customerId int
     */
    public Appointments(int appointmentId, String title, String description, String type, LocalDateTime startTime,
                        LocalDateTime endTime, int customerId) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.customerId = customerId;
    }

    /**
     * Default overloaded constructor for Appointments.
     * @param appointmentId int
     * @param title String
     * @param description String
     * @param location String
     * @param type String
     * @param startTime LocalDateTime
     * @param endTime LocalDateTime
     * @param customerId int
     * @param customerName String
     * @param userId int
     * @param userName String
     * @param contactId int
     * @param contactName String
     */
    public Appointments(int appointmentId, String title, String description, String location, String type,
                        LocalDateTime startTime, LocalDateTime endTime, int customerId, String customerName, int userId,
                        String userName, int contactId, String contactName) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.customerId = customerId;
        this.customerName = customerName;
        this.userId = userId;
        this.userName = userName;
        this.contactId = contactId;
        this.contactName = contactName;
    }

    /**
     * Accessor (getter) method gets and returns the month of the appointment.
     * @return month the month of the appointment
     */
    public String getMonth() {
        return month;
    }

    /**
     * Accessor (getter) method gets and returns the number of occurrences of appointments
     * @return occurrences of appointments
     */
    public int getOccurrences() {
        return occurrences;
    }

    /**
     * Accessor (getter) method gets and returns the month of the appointment.
     * @return appointmentId the appointment ID
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     * Mutator (setter) method sets this.appointmentId to appointmentId to allow access to the instance variable from
     * another class.
     * @param appointmentId int appointmentId
     */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * Accessor (getter) method gets and returns the title of the appointment.
     * @return title the title of the appointment
     */
    public String getTitle() {
        return title;
    }

    /**
     * Mutator (setter) method sets this.appointmentId to appointmentId to allow access to the instance variable from
     * another class.
     * @param title sets the new title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Accessor (getter) method gets and returns the description.
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Mutator (setter) method sets this.appointmentId to appointmentId to allow access to the instance variable from
     * another class.
     * @param description String
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Accessor (getter) method gets and returns the location.
     * @return location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Mutator (setter) method sets this.location to appointmentId to allow access to the instance variable from
     * another class.
     * @param location sets the new location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Accessor (getter) method gets and returns the type.
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Mutator (setter) method sets this.type to type to allow access to the instance variable from
     * another class.
     * @param type sets the new type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Accessor (getter) method gets and returns the startTime.
     * @return startTime
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Mutator (setter) method sets this.startTime to startTime to allow access to the instance variable from
     * another class.
     * @param startTime sets the new startTime
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Accessor (getter) method gets and returns the endTime.
     * @return endTime
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Mutator (setter) method sets this.endTime to endTime to allow access to the instance variable from
     * another class.
     * @param endTime sets the new end time
     */
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * Accessor (getter) method gets and returns the customerId.
     * @return customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Mutator (setter) method sets this.customerId to customerId to allow access to the instance variable from
     * another class.
     * @param customerId sets the new customerId
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Accessor (getter) method gets and returns the userId.
     * @return userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Mutator (setter) method sets this.userId to userId to allow access to the instance variable from
     * another class.
     * @param userId sets the new userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Accessor (getter) method gets and returns the contactId.
     * @return contactId
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * Mutator (setter) method sets this.contactId to contactId to allow access to the instance variable from
     * another class.
     * @param contactId sets the new contact ID
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * Accessor (getter) method gets and returns the contactName.
     * @return contactName
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Mutator (setter) method sets this.contactName to contactName to allow access to the instance variable from
     * another class.
     * @param contactName sets the new contact name
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * Accessor (getter) method gets and returns the customerName.
     * @return customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Mutator (setter) method sets this.customerName to customerName to allow access to the instance variable from
     * another class.
     * @param customerName sets the new customer name
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Accessor (getter) method gets and returns the userName.
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Mutator (setter) method sets this.userName to username to allow access to the instance variable from
     * another class.
     * @param userName sets the new userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
}


