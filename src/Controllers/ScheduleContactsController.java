package Controllers;

import DBAccess.DBAppointments;
import DBAccess.DBContacts;
import Main.InterfaceAppointmentForContacts;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import Model.*;

import java.time.LocalDateTime;

/**
 * This public class ScheduleContactsController handles all of the alerts. Controller class handles the
 * ScheduleContacts.fxml file.
 * @author Krystal Lee
 * @version 1.0
 * @since Summer 2021
 */
public class ScheduleContactsController {

    @FXML
    private TableView<Appointments> contactsScheduleTable;

    @FXML
    private TableColumn<Appointments, Integer> aptIdCol;

    @FXML
    private TableColumn<Appointments, String> aptTitleCol;

    @FXML
    private TableColumn<Appointments, String> aptDescriptionCol;

    @FXML
    private TableColumn<Appointments, String> aptTypeCol;

    @FXML
    private TableColumn<Appointments, LocalDateTime> aptStartCol;

    @FXML
    private TableColumn<Appointments, LocalDateTime> aptEndCol;

    @FXML
    private TableColumn<Appointments, Integer> aptCustomerIdCol;

    @FXML
    private ComboBox<Contacts> contactComboBox;

    private static ObservableList<Appointments> appointments(int selectedContact) {
        ObservableList<Appointments> contactScheduleReport = DBAppointments.getAppointmentScheduleForContact( selectedContact );
        return contactScheduleReport;
    }

    /**
     * This void method initializes the ComboBox with all the contacts available from the database.
     */
    @FXML
    public void initialize() {
        contactComboBox.setItems(DBContacts.getAllContacts());
        contactComboBox.setPromptText("Choose Contact");
        contactComboBox.setVisibleRowCount(5);
    }


    /**
     * The lambda expression provided here is in isolation from other classes. In future case, we can just use this
     * expression to get the ObservableList of contactScheduleReport without having to define the method again.
     */
    SimpleObjectProperty<InterfaceAppointmentForContacts> appointmentsForContacts = new SimpleObjectProperty
            <>( this, "appointmentsForContacts", ScheduleContactsController::appointments );

    /**
     * This void method handles the change in ComboBox change and loads all the appointments for the chosen contact.
     */
    @FXML
    public void contactComboBoxChangeAction() {
        int selectedContactId = contactComboBox.getValue().getContactId();
        appointmentsForContacts.get().appointments(selectedContactId);
        contactsScheduleTable.setItems( appointmentsForContacts.get().appointments(selectedContactId));
        aptIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        aptTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        aptDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        aptTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        aptStartCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        aptEndCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        aptCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        contactsScheduleTable.getSortOrder().add(aptStartCol);
    }

}
