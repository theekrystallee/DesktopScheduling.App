package DBAccess;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Model.*;
import Database.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class communicates with the database to query the Countries.
 */
public class DBCountries {

    /**
     * This static method returns a list of all Countries from the database.
     */
    /**
     *
     * @return
     */
    public static ObservableList<Countries> getAllCountries() {
        ObservableList<Countries> countriesList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM countries";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int countryId = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");
                Countries C = new Countries(countryId, countryName);
                countriesList.add(C);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return countriesList;
    }
}
