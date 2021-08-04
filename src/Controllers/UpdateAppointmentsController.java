package Controllers;

import DBAccess.DBAppointments;
import DBAccess.DBContacts;
import DBAccess.DBCustomers;
import DBAccess.DBUsers;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Model.Appointments;
import Model.Contacts;
import Model.Customers;
import Model.Users;
import Database.TimeZoneConversion;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


/**
 * This public class UpdateAppointmentsController handles all of the alerts. Controller class handles the
 * UpdateAppointmentsScreen.fxml file.
 * @author Krystal Lee
 * @version 1.0
 * @since Summer 2021
 */
public class UpdateAppointmentsController {

    @FXML
    private TextField appointmentTitleTextField;

    @FXML
    private TextArea appointmentDescriptionTextArea;

    @FXML
    private TextField appointmentLocationTextField;

    @FXML
    private DatePicker appointmentDatePicker;

    @FXML
    private ComboBox<LocalTime> startComboBox;

    @FXML
    private ComboBox<LocalTime> endComboBox;

    @FXML
    private ComboBox<Contacts> contactComboBox;

    @FXML
    private ComboBox<Customers> customerComboBox;

    @FXML
    private ComboBox<Users> userComboBox;

    @FXML
    private Label appointmentIdLabel;

    @FXML
    private ToggleGroup appointTypeToggleGroup;

    @FXML
    private RadioButton phoneMeetingRadioButton;

    @FXML
    private RadioButton videoConferenceRadioButton;

    @FXML
    private RadioButton inPersonRadioButton;


    /**
     * Initializes the contacts, customer, user ComboBox with data from the database.
     */
    @FXML
    public void initialize() {
        //for contacts ComboBox
        contactComboBox.setItems(DBContacts.getAllContacts());
        contactComboBox.setPromptText("Choose Contact");
        contactComboBox.setVisibleRowCount(5);

        //for customers ComboBox
        customerComboBox.setItems(DBCustomers.getAllCustomers());
        customerComboBox.setPromptText("Choose Customer");
        customerComboBox.setVisibleRowCount(5);

        //for users ComboBox
        userComboBox.setItems( DBUsers.getAllUsers());
        userComboBox.setPromptText("Choose User");
        userComboBox.setVisibleRowCount(5);

        //for appointment time ComboBox
        LocalTime start = LocalTime.of(00, 0);
        LocalTime end = LocalTime.of(23, 30);

        while (start.isBefore(end.plusSeconds(1))) {
            startComboBox.getItems().add(start);
            endComboBox.getItems().add(start);
            start = start.plusMinutes(15);
        }
    }

    /**
     * Receives the selected appointment object to update and then populates the fields and ComboBoxes with the object values.
     */
    @FXML
    public void populateAppointmentForm(Appointments passedAppointment) {
        //System.out.println(passedAppointment.getTitle());
        appointmentTitleTextField.setText(passedAppointment.getTitle());
        appointmentDescriptionTextArea.setText(passedAppointment.getDescription());
        appointmentLocationTextField.setText(passedAppointment.getLocation());
        //appointmentTypeTextField.setText(passedAppointment.getType());
        if (passedAppointment.getType().equals("Phone Meeting")) {
            phoneMeetingRadioButton.fire();
        }
        if (passedAppointment.getType().equals("Video Conference")) {
            videoConferenceRadioButton.fire();
        }
        if (passedAppointment.getType().equals("In-Person Meeting")) {
            inPersonRadioButton.fire();
        }
        appointmentDatePicker.setValue(passedAppointment.getStartTime().toLocalDate());
        startComboBox.setValue(passedAppointment.getStartTime().toLocalTime());
        endComboBox.setValue(passedAppointment.getEndTime().toLocalTime());
        appointmentIdLabel.setText(String.valueOf(passedAppointment.getAppointmentId()));
        int passedContactId = passedAppointment.getContactId();
        int passedCustomerId = passedAppointment.getCustomerId();
        int passedUserId = passedAppointment.getUserId();

        for (Contacts element : contactComboBox.getItems()) {
            if (passedContactId == element.getContactId()) {
                contactComboBox.getSelectionModel().select(element);
            }
        }

        for (Customers element : customerComboBox.getItems()) {
            if (passedCustomerId == element.getCustomerId()) {
                customerComboBox.getSelectionModel().select(element);
            }
        }

        for (Users element : userComboBox.getItems()) {
            if (passedUserId == element.getUserId()) {
                userComboBox.getSelectionModel().select(element);
            }
        }
    }

    /**
     * Updates the changes to the selected appointment by creating a new object by getting the values from the form fields.
     */
    @FXML
    public void updateChangesToAppointment(MouseEvent event) throws IOException {
        int appointmentId = Integer.parseInt(appointmentIdLabel.getText());
        String title = appointmentTitleTextField.getText().trim();
        String description = appointmentDescriptionTextArea.getText().trim();
        String location = appointmentLocationTextField.getText().trim();
        String type = "";
        if (phoneMeetingRadioButton.isSelected()) {
            type = "Phone Meeting";
        }
        if (videoConferenceRadioButton.isSelected()) {
            type = "Video Conference";
        }
        if (inPersonRadioButton.isSelected()) {
            type = "In-Person Meeting";
        }
        LocalTime startTime = startComboBox.getValue();
        LocalTime endTime = endComboBox.getValue();
        LocalDate appointmentDate = appointmentDatePicker.getValue();
        LocalDateTime appointmentStartDateAndTime = LocalDateTime.of(appointmentDate, startTime);
        LocalDateTime appointmentEndDateAndTime = LocalDateTime.of(appointmentDate, endTime);
        int customerId = customerComboBox.getValue().getCustomerId();
        String customerName = customerComboBox.getValue().getCustomerName();
        int userId = userComboBox.getValue().getUserId();
        String userName = userComboBox.getValue().getUserName();
        int contactId = contactComboBox.getValue().getContactId();
        String contactName = contactComboBox.getValue().getContactName();

        //Creating an observable list to see if it returns null or values
        ObservableList<Appointments> overlappingAppointmentsForCustomer =
                DBAppointments.getOverlappingAppointmentsForCustomersMinusSelectedAppointment(appointmentStartDateAndTime,
                        appointmentEndDateAndTime, customerId, appointmentId);

        int conversionResult = TimeZoneConversion.estConversion(appointmentStartDateAndTime, appointmentEndDateAndTime);




        if (conversionResult == 1) {
            AlertMessageController.businessClosedError();
        } else if (appointmentEndDateAndTime.isBefore(appointmentStartDateAndTime)) {
            AlertMessageController.endTimeBeforeStartTimeError();
        } else if ((title.trim().isEmpty()) || (description.trim().isEmpty()) || (location.trim().isEmpty()) ||
                (type.trim().isEmpty())) {
            AlertMessageController.nullValueEntry();
        } else if (overlappingAppointmentsForCustomer.size() != 0) {//if it is not null, it means it returned something. i.e. that customer had overlapping appointments
            for (Appointments element : overlappingAppointmentsForCustomer) {
                AlertMessageController.appointmentForCustomersOverlap(element);
            }
        } else {

            Appointments updatedAppointmentObject = new Appointments(appointmentId, title, description, location, type,
                    appointmentStartDateAndTime, appointmentEndDateAndTime, customerId, customerName, userId, userName, contactId, contactName);
            DBAppointments.updateSelectedAppointment(updatedAppointmentObject);

            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(getClass().getResource("/Views/AppointmentsScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    /**
     * Cancels the update and returns users to the main appointment screen.
     */
    @FXML
    public void cancelUpdateAppointment(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/Views/AppointmentsScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
