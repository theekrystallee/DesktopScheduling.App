package DBAccess;

import Database.*;
import Model.*;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This DBContacts class interacts with the database to query the contacts in the database.
 */
public class DBContacts {

    /**
     * This observable list method gets a list of all contacts from the database and returns contacts objects.
     * @return allContacts
     */
    public static ObservableList<Contacts> getAllContacts() {
        ObservableList<Contacts> allContacts = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM contacts";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int contactId = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String contactEmail = rs.getString("Email");
                Contacts contact = new Contacts(contactId, contactName, contactEmail);
                allContacts.add(contact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allContacts;
    }
}