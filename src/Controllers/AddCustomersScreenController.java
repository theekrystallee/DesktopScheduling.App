package Controllers;

import DBAccess.DBCountries;
import DBAccess.DBCustomers;
import DBAccess.DBFirstLevelDivision;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Model.*;

import java.io.IOException;

/**
 * This controller class handles the AddCustomersScreen.fxml file.
 * @author Krystal Lee
 * @version 1.0
 * @since Summer 2021
 */
public class AddCustomersScreenController {

    Stage stage;
    Parent scene;

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
     * Initializes the add customer form and sets ComboBox with database values (Country and Division).
     */
    @FXML
    public void initialize() {
        divisionComboBox.getItems().clear();
        divisionComboBox.setItems(DBFirstLevelDivision.getAllFirstLevelDivisions());
        divisionComboBox.setPromptText("Choose Division");
        divisionComboBox.setVisibleRowCount(5);

        countryComboBox.getItems().clear();
        countryComboBox.setItems(DBCountries.getAllCountries());
        countryComboBox.setPromptText("Choose Country");
        countryComboBox.setVisibleRowCount(5);

    }

    /**
     * This void method handles save button and input is sent to the database. New input creates a new Customers object
     * is created and sent to DBCustomers.addNewCustomer method and saved.
     * @param event MouseEvent
     * @throws IOException exception thrown
     */
    @FXML
    public void saveNewCustomerClicked(MouseEvent event) throws IOException {
        try {
            int id = 0;
            String name = nameTextField.getText().trim();
            String address = addressTextField.getText().trim();
            String postal = postalTextField.getText().trim();
            String phone = phoneTextField.getText().trim();
            //System.out.println("text field name is " + name);
            int divisionId = divisionComboBox.getValue().getDivisionId();
            String divisionName = divisionComboBox.getValue().getDivisionName();
            Customers newCustomer = new Customers(id, name, address, postal, phone, divisionId, divisionName);
            DBCustomers.addNewCustomer(newCustomer);

            //use this to close if not modal
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/Views/CustomersScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (NullPointerException exp) {
            AlertMessageController.nullValueEntry();
        }
}

    /**
     * This handles filtering the divisions value in the divisions combo box.
     */
    public void countryComboBoxValueChange() {
        divisionComboBox.getItems().clear();
        int selectedCountryId = countryComboBox.getValue().getCountryId();
        ObservableList<FirstLevelDivisions> filteredDivision =
                DBFirstLevelDivision.getFilteredDivisions(selectedCountryId);
        divisionComboBox.setItems(filteredDivision);
        divisionComboBox.getSelectionModel().selectFirst();
    }

    /**
     * This public void method handles canceling the save and closes the form.
     * @param event MouseEvent
     * @throws IOException exception thrown
     */
    @FXML
    public void cancelSaveButtonClicked(MouseEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/Views/CustomersScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

}
