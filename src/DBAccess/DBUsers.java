package DBAccess;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Model.*;
import Database.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.lang.*;

/**
 * This DBUsers class communicates with the database to query for list of users.
 */
public class DBUsers {

    /**
     * This method returns an observable list all of the users from the database.
     * @return allUsers
     */
    public static ObservableList<Users> getAllUsers() {
        ObservableList<Users> allUsers = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM users";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int userId = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String userPassword = rs.getString("Password");
                Users user = new Users(userId, userName, userPassword);
                allUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allUsers;
    }

    /**
     * This method returns user object of the logged in user.
     * @param passedUserName String
     * @param passedUserPassword String
     * @return null
     */
    public static Users loginUser(String passedUserName, String passedUserPassword) {

        try {
            String sql = "SELECT * FROM users WHERE BINARY User_Name = ? AND BINARY Password = ?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.setString(1, passedUserName);
            ps.setString(2, passedUserPassword);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int userId = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String userPassword = rs.getString("Password");
                Users matchedUser = new Users(userId, userName, userPassword);
                return matchedUser;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
