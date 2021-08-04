package Model;

import java.lang.*;


/**
 * This Users class allows the program to create and modify the Users objects.
 */
public class Users {

    private int userId;
    private String userName;
    private String password;

    /**
     * Default constructor for the Users class.
     * @param userId in
     * @param userName String
     * @param password String
     */
    public Users(int userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    /**
     * Accessor (getter) method gets and returns the user Id.
     * @return userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Mutator (setter) method sets the user Id.
     * @param userId int
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Accessor (getter) method gets and returns the userName.
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Mutator (setter) method sets the userName.
     * @param userName String
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Accessor (getter) method gets and returns the password.
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Mutator (setter) method sets the password.
     * @param password String
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This Override method that is needed to display the name of the users on the ComboBox instead of the random
     * object reference.
     */
    @Override
    public String toString() {
        return userName;
    }
}
