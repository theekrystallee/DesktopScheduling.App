package Model;

import java.lang.*;


/**
 * This Countries class allows the program to create and modify the Countries objects.
 */
public class Countries {

    private int countryId;
    private String countryName;

    /**
     * Default constructor for the Countries class.
     * @param countryId int
     * @param countryName String
     */
    public Countries(int countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
    }

    /**
     * Returns the country's id
     * @return countryId
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * Returns the country's name
     * @return countryName
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * This Override method that is needed to display the name of the Country on the ComboBox instead of the random object reference.
     */
    @Override
    public String toString() {
        return getCountryName();
    }
}
