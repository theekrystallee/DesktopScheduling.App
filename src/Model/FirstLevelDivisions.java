package Model;



/**
 * This FirstLevelDivisions class allows the program to create and modify the FirstLevelDivisions objects.
 */
public class FirstLevelDivisions {

    private int divisionId;
    private String divisionName;
    private int countryId;

    /**
     * Default constructor for first-level division class
     */
    public FirstLevelDivisions(int divisionId, String divisionName, int countryId) {
        this.divisionId = divisionId;
        this.divisionName = divisionName;
        this.countryId = countryId;
    }

    /**
     * Accessor (getter) method gets and returns the division Id
     * @return divisionId
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * Mutator (setter) method sets the division Id.
     * @param divisionId int
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /**
     * Accessor (getter) method gets and returns the division Id
     * @return divisionId
     */
    public String getDivisionName() {
        return divisionName;
    }

    /**
     * Mutator (setter) method sets the division name.
     * @param divisionName String
     */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    /**
     * Accessor (getter) method gets and returns the country Id
     * @return countryId
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * Mutator (setter) method sets the country Id.
     * @param countryId countryId
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * This Override method that is needed to display the name of the division on the ComboBox instead of the random object reference.
     */
    @Override
    public String toString() {
        return getDivisionName();
    }
}
