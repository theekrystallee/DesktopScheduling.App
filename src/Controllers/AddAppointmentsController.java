package Controllers;

import DBAccess.*;
import Model.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Database.TimeZoneConversion;
import java.io.IOException;
import java.time.*;

/**
 * This is the AddAppointmentController class that handles the AddAppointmentScreen.fxml file.
 * @author Krystal Lee
 * @version 1.0
 * @since Summer 2021
 */
public class AddAppointmentsController {

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
    private ToggleGroup appointTypeToggleGroup;

    @FXML
    private RadioButton phoneMeetingRadioButton;

    @FXML
    private RadioButton videoConferenceRadioButton;

    @FXML
    private RadioButton inPersonRadioButton;


    /**
     * This method initializes the ComboBoxes in the add appointment screen.
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

        //for start appointment time
        LocalTime start = LocalTime.of(00, 0);
        LocalTime end = LocalTime.of(23, 30);

        while (start.isBefore(end.plusSeconds(1))) {
            startComboBox.getItems().add(start);
            endComboBox.getItems().add(start);
            start = start.plusMinutes(15);
        }
    }

    /**
     * This handles the save button on the AddAppointmentScreen and saves the new input to the database. This screen/form
     * collects the user input values from the save form and saves it into the Appointments class and passes to the
     * DBAppointments.addNewAppointment method. The method uses Lambda expression to iterate through overlapping
     * appointments instead of using a for loop to iterate.
     * @param event MouseEvent
     * @throws IOException for add appointment combo boxes
     */
    public void saveNewAppointment(MouseEvent event) throws IOException {
        try {
            int id = 0;
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

            //Creating an observable list to see if it returns null or values, if it returns some values, then the customer has overlapping appointments
            ObservableList<Appointments> customersWithOverlappingAppointments =
                    DBAppointments.getAppointmentsForCustomers(appointmentStartDateAndTime, appointmentEndDateAndTime, customerId);

            int conversionResult = TimeZoneConversion.estConversion(appointmentStartDateAndTime, appointmentEndDateAndTime);

            if (conversionResult == 1) {
                AlertMessageController.businessClosedError();
            } else if (appointmentEndDateAndTime.isBefore(appointmentStartDateAndTime)) {
                AlertMessageController.endTimeBeforeStartTimeError();
            } else if ((title.trim().isEmpty()) || (description.trim().isEmpty()) || (location.trim().isEmpty())
                    || (type.trim().isEmpty())) {
                AlertMessageController.nullValueEntry();
            } else if (customersWithOverlappingAppointments.size() != 0) {
                //if there are some values returned, then show alert and give details of the overlapping appointments
                customersWithOverlappingAppointments.forEach((element) -> {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Customer Appointment Overlap");
                    errorAlert.setContentText("Customer " + element.getCustomerName() +
                            " already has another appointment on that timeslot. Here are the details." +
                            " \n Title : " + element.getTitle() + "\n Description : " + element.getDescription() +
                            " \n Start Time : " + element.getStartTime() + " \n End Time : " + element.getEndTime());
                    errorAlert.showAndWait();
                });
            } else {
                Appointments newAppointment = new Appointments(id, title, description, location, type,
                        appointmentStartDateAndTime, appointmentEndDateAndTime, customerId, customerName, userId,
                        userName, contactId, contactName);
                DBAppointments.addNewAppointment(newAppointment);

                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Parent scene = FXMLLoader.load(getClass().getResource("/Views/AppointmentsScreen.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }
        } catch (NullPointerException e) {
            AlertMessageController.errorAddingAppointment();
        }
    }

    /**
     * This method handles the cancel button returns to the appointment screen.
     * @param event MouseEvent
     * @throws IOException exception for the cancel button
     */
    @FXML
    public void cancelAddAppointment(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/AddAppointmentsController.fxml"));
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Appointment Form");
        stage.setScene(scene);
        stage.show();
    }
}

