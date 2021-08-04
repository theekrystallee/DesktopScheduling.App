package Controllers;

import DBAccess.DBCountries;
import DBAccess.DBCustomers;
import DBAccess.DBFirstLevelDivision;
import Model.FirstLevelDivisions;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Model.*;
import java.io.IOException;

/**
 * This public class UpdateCustomersController handles all of the alerts. Controller class handles the
 * UpdateCustomersScreen.fxml file.
 * @author Krystal Lee
 * @version 1.0
 * @since Summer 2021
 */
public class UpdateCustomersController {

    Stage stage;
    Parent scene;

    @FXML
    private Label customerIdLabel;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField addressTextField;

    @FXML
    private TextField postalTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private ComboBox<FirstLevelDivisions> divisionComboBox;

    @FXML
    private ComboBox<Countries> countryComboBox;

    /**
     * Initializes the Division and Country ComboBoxes with all values from the database.
     */
    @FXML
    public void initialize() {
        //divisionComboBox.getItems().clear();
        divisionComboBox.setItems(DBFirstLevelDivision.getAllFirstLevelDivisions());
        divisionComboBox.setPromptText("Choose Division");
        divisionComboBox.setVisibleRowCount(5);

        //countryComboBox.getItems().clear();
        countryComboBox.setItems(DBCountries.getAllCountries());
        countryComboBox.setPromptText("Choose Country");
        countryComboBox.setVisibleRowCount(5);
    }

    /**
     * Receives the selected customers object to update and then populates the fields and ComboBoxes with the object values.
     */
    @FXML
    public void populateUpdateForm(Customers passedCustomer) {
        customerIdLabel.setText(String.valueOf(passedCustomer.getCustomerId()));
        nameTextField.setText(passedCustomer.getCustomerName());
        addressTextField.setText(passedCustomer.getCustomerAddress());
        postalTextField.setText(passedCustomer.getCustomerPostalCode());
        phoneTextField.setText(passedCustomer.getCustomerPhone());
        int passedDivisionId = passedCustomer.getDivisionId();
        System.out.println("passed customers id is " + passedCustomer.getCustomerId());
        System.out.println(passedDivisionId);
        int countryId = 0;

        for (FirstLevelDivisions element : divisionComboBox.getItems()) {
            if (passedDivisionId == element.getDivisionId()) {
                //System.out.println("match found at " + element.getDivisionId());
                countryId = element.getCountryId();
                //divisionComboBox.setValue(DBFirstLevelDivision.getAllFirstLevelDivisions().get(1));
                divisionComboBox.getSelectionModel().select(element);
                break;
            }
        }

        for (Countries element : countryComboBox.getItems()) {
            if (countryId == element.getCountryId()) {
                countryComboBox.getSelectionModel().select(element);
                break;
            }
        }

    }

    /**
     * Updates the changes to the selected customer by creating a new object by getting the values from the form fields.
     */
    public void updateCustomer(MouseEvent event) throws IOException {
        int id = Integer.parseInt(customerIdLabel.getText());
        String name = nameTextField.getText().trim();
        String address = addressTextField.getText().trim();
        String postal = postalTextField.getText().trim();
        String phone = phoneTextField.getText().trim();
        //System.out.println("text field name is " + name);
        int divisionId = divisionComboBox.getValue().getDivisionId();
        String divisionName = divisionComboBox.getValue().getDivisionName();
        Customers updateThisCustomer = new Customers(id, name, address, postal, phone, divisionId, divisionName);
        DBCustomers.updateCustomer(updateThisCustomer);
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/Views/UpdateCustomersScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Tracks the change in Country ComboBox values and filters divisions values in divisions ComboBox.
     */
    public void countryComboBoxValueChange() {
        divisionComboBox.getItems().clear();
        int selectedCountryId = countryComboBox.getValue().getCountryId();
        ObservableList<FirstLevelDivisions> filteredDivision = DBFirstLevelDivision.getFilteredDivisions(selectedCountryId);
        divisionComboBox.setItems(filteredDivision);
        divisionComboBox.getSelectionModel().selectFirst();
    }

    /**
     * Cancels the update and returns users to the main customer screen.
     */
    @FXML
    public void cancelUpdateButton(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("../Views/CustomersScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

}
