package DBAccess;

import Database.*;
import Controllers.AlertMessageController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ButtonType;
import Model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

/**
 * This DBCustomers class communicates with the database and queries it for customers list.
 */
public class DBCustomers {

    /**
     * This method queries the database and gets a list of all customers.
     * @return customersList
     */
    public static ObservableList<Customers> getAllCustomers() {
        ObservableList<Customers> customersList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, " +
                    "customers.Division_ID, Division FROM customers INNER JOIN first_level_divisions " +
                    "ON customers.Division_ID = first_level_divisions.Division_ID";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String customerAddress = rs.getString("Address");
                String customerPostalCode = rs.getString("Postal_Code");
                String customerPhone = rs.getString("Phone");
                int divisionId = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");
                Customers customers = new Customers(id, customerName, customerAddress, customerPostalCode, customerPhone, divisionId, divisionName);
                customersList.add(customers);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customersList;
    }

    /**
     * This observable list method looks up customers using customer Id.
     * @param customerId int
     * @return null
     */
    public static ObservableList<Customers> lookupCustomers(int customerId) {

        ObservableList<Customers> searchedSingleCustomer = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, customers.Division_ID, " +
                    "Division FROM customers INNER JOIN first_level_divisions ON customers.Division_ID = " +
                    "first_level_divisions.Division_ID WHERE Customer_ID = ?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String customerAddress = rs.getString("Address");
                String customerPostalCode = rs.getString("Postal_Code");
                String customerPhone = rs.getString("Phone");
                int divisionId = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");
                Customers singleFoundCustomer = new Customers(id, customerName, customerAddress, customerPostalCode,
                        customerPhone, divisionId, divisionName);
                //System.out.println(singleFoundCustomer.getCustomerName() + " is the name");
                //return singleFoundCustomer;
                searchedSingleCustomer.add(singleFoundCustomer);
                return searchedSingleCustomer;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This observable list method looks up the customers by customer name and returns matching customers object in an
     * observable list.
     * @param customerName String
     * @return matchedCustomers
     */
    public static ObservableList<Customers> lookupCustomers(String customerName) {
        ObservableList<Customers> matchedCustomers = FXCollections.observableArrayList();

        try {
            String sql = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, customers.Division_ID, " +
                    "Division FROM customers INNER JOIN first_level_divisions ON customers.Division_ID = " +
                    "first_level_divisions.Division_ID WHERE Customer_Name LIKE ?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setString(1, customerName + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("Customer_ID");
                String foundCustomerName = rs.getString("Customer_Name");
                String customerAddress = rs.getString("Address");
                String customerPostalCode = rs.getString("Postal_Code");
                String customerPhone = rs.getString("Phone");
                int divisionId = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");
                Customers customer = new Customers(id, foundCustomerName, customerAddress, customerPostalCode, customerPhone, divisionId, divisionName);
                matchedCustomers.add(customer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matchedCustomers;

    }

    /**
     * This static method received the Customers object from other classes to save them in the database.
     * @param passedCustomer Customers
     */
    public static void addNewCustomer(Customers passedCustomer) {
        try {
            String sql = "INSERT INTO customers(Customer_Name, Address, Postal_Code, Phone, Division_ID)" +
                    " VALUES(?, ?, ?, ?, ?)";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setString(1, passedCustomer.getCustomerName());
            ps.setString(2, passedCustomer.getCustomerAddress());
            ps.setString(3, passedCustomer.getCustomerPostalCode());
            ps.setString(4, passedCustomer.getCustomerPhone());
            ps.setInt(5, passedCustomer.getDivisionId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This static method received the customers object to be updated and then updates in the database based on the
     * customerId.
     * @param passedCustomer Customers
     */
    public static void updateCustomer(Customers passedCustomer) {
        try {
            String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, " +
                    "Division_ID = ? WHERE Customer_ID = ?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setString(1, passedCustomer.getCustomerName());
            ps.setString(2, passedCustomer.getCustomerAddress());
            ps.setString(3, passedCustomer.getCustomerPostalCode());
            ps.setString(4, passedCustomer.getCustomerPhone());
            ps.setInt(5, passedCustomer.getDivisionId());
            ps.setInt(6, passedCustomer.getCustomerId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This static method deletes the customer based on the customer Id. If customer has appointments, the deletion
     * is restricted and an error message is triggered. Upon confirmation from the user, all appointments for the customer
     * are deleted.
     * @param customerId int
     */
    public static void deleteCustomer(int customerId) {
        try {
            String sql = "DELETE FROM customers WHERE Customer_ID = ?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, customerId);
            try {
                ps.executeUpdate();
                AlertMessageController.deleteSuccessfulWithoutAppointment(customerId);
            } catch (SQLIntegrityConstraintViolationException e) {
                Optional<ButtonType> answer = AlertMessageController.customerHasAppointmentsError();
                if (answer.isPresent() && answer.get() == ButtonType.OK) {
                    try {
                        String sqli = "DELETE FROM appointments WHERE Customer_ID = ?";
                        PreparedStatement psi = DBConnection.getConnection().prepareStatement(sqli);
                        psi.setInt(1, customerId);

                        psi.executeUpdate();
                        //Delete successful
                        AlertMessageController.deleteAppointmentSuccessfulNowDeleteCustomer(customerId);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<Customers> lookupCustomers() {
        return null;
    }
}
