package Model;

import java.lang.*;
/**
 * This Customers class allows the program to create and modify the Customers objects.
 */
public class Customers {

    private int customerId;
    private String customerName;
    private String customerAddress;
    private String customerPostalCode;
    private String customerPhone;
    private int divisionId;
    private String divisionName;

    /**
     * Default constructor for the Customers class.
     * @param customerId int
     * @param customerName String
     * @param customerAddress String
     * @param customerPostalCode int
     * @param customerPhone String
     * @param divisionId int
     * @param divisionName String
     */
    public Customers(int customerId, String customerName, String customerAddress, String customerPostalCode,
                     String customerPhone, int divisionId, String divisionName) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostalCode = customerPostalCode;
        this.customerPhone = customerPhone;
        this.divisionId = divisionId;
        this.divisionName = divisionName;
    }

    /**
     *  Accessor (getter) method gets and returns customerId
     * @return customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     *  Accessor (getter) method gets and returns customerName
     * @return customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     *  Accessor (getter) method gets and returns customerAddress
     * @return customerAddress
     */
    public String getCustomerAddress() {
        return customerAddress;
    }

    /**
     *  Accessor (getter) method gets and returns customerPostalCode
     * @return customerPostalCode
     */
    public String getCustomerPostalCode() {
        return customerPostalCode;
    }

    /**
     *  Accessor (getter) method gets and returns customerPhone
     * @return customerPhone
     */
    public String getCustomerPhone() {
        return customerPhone;
    }

    /**
     *  Accessor (getter) method gets and returns divisionId
     * @return divisionId
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     *  Accessor (getter) method gets and returns divisionName
     * @return divisionName
     */
    public String getDivisionName() {
        return divisionName;
    }

    /**
     * This Override method that is needed to display the name of the customer on the ComboBox instead of the random
     * object reference.
     */
    @Override
    public String toString() {
        return customerName;
    }
}
