package DBAccess;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import Model.*;
import Database.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This DBFirstLevelDivision class communicates with the database to query first level division data.
 */
public class DBFirstLevelDivision {

    /**
     * This static observable list returns all first level divisions list.
     * @return allFirstLevelDivisions
     */
    public static ObservableList<FirstLevelDivisions> getAllFirstLevelDivisions() {
        //public static ComboBox<FirstLevelDivisions> getAllFirstLevelDivisions() {

        ObservableList<FirstLevelDivisions> allFirstLevelDivisions = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM FirstLevelDivisions";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("Division_ID");
                String name = rs.getString("Division");
                int countryId = rs.getInt("COUNTRY_ID");
                FirstLevelDivisions firstLevelDiv = new FirstLevelDivisions(id, name, countryId);
                allFirstLevelDivisions.add(firstLevelDiv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allFirstLevelDivisions;
    }

    /**
     * This observable list method returns the selected/filtered first level divisions by using the country Id.
     * @param countryComboBoxValue int
     * @return filteredFirstLevelDivisions
     */
    public static ObservableList<FirstLevelDivisions> getFilteredDivisions(int countryComboBoxValue) {
        ObservableList<FirstLevelDivisions> filteredFirstLevelDivisions = FXCollections.observableArrayList();

        //System.out.println("passed country id is : " + countryComboBoxValue);
        try {
            String sql = "SELECT * FROM first_level_divisions WHERE Country_ID = ?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, countryComboBoxValue);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("Division_ID");
                String name = rs.getString("Division");
                int countryId = rs.getInt("COUNTRY_ID");
                FirstLevelDivisions firstLevelDiv = new FirstLevelDivisions(id, name, countryId);
                filteredFirstLevelDivisions.add(firstLevelDiv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return filteredFirstLevelDivisions;
    }

}
