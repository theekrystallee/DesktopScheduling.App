package Controllers;

import Model.Appointments;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This public Controller class AlertMessageController handles all of the alerts. Public static method is used when alerts are
 * needed. Controller class handles the AlertMessage.fxml file.
 * @author Krystal Lee
 * @version 1.0
 * @since Summer 2021
 */
public class AlertMessageController {

    /**
     * this static method handles displaying the alert messasge when the logged in user has an appointment starting
     * within 15 minutes of login time.
     * @param name String of the logged in user
     * @param numberOfAppointments int the number of appointments the logged in user has
     * @return results the result is displayed as an alert message
     */
    public static Optional<ButtonType> userHasAppointmentsSoonAlert(String name, int numberOfAppointments,
                                                                    LocalDateTime localDateTime) {
        Alert appointmentAlert = new Alert(Alert.AlertType.CONFIRMATION);
        appointmentAlert.setTitle("Upcoming Appointments");
        appointmentAlert.setContentText("Hello " + name + ",\nyou have " + numberOfAppointments +
                " upcoming appointment(s) within the next 15 minutes. Would you like to view them?\n" +
                "Appointment date and time: "  + localDateTime);
        Optional<ButtonType> result = appointmentAlert.showAndWait();
        return result;
    }

    /**
     * This static void method displays the alert message if the logged in user has no appointment.
     * @param name String the name of the logged in user
     */
    public static void userHasNoAppointmentsSoonAlert(String name) {
        Alert noAppointmentAlert = new Alert(Alert.AlertType.INFORMATION);

        ResourceBundle resBundle = ResourceBundle.getBundle("lang_fr", Locale.getDefault());
        if (Locale.getDefault().getLanguage().equals("fr")) {
            noAppointmentAlert.setTitle("pas de rendez-vous à venir");
            noAppointmentAlert.setContentText("Salut " + name + ",\n vous n'avez pas de rendez-vous à venir dans les 15 prochaines minutes.");
//            noAppointmentAlert.showAndWait();
        }
        if (Locale.getDefault().getLanguage().equals( "en" )) {
            noAppointmentAlert.setTitle("No Upcoming Appointments");
            noAppointmentAlert.setContentText("Hi " + name + ",\n you don't have any upcoming appointments within the next 15 minutes.");
//            noAppointmentAlert.showAndWait();
        }
        noAppointmentAlert.showAndWait();
    }

    /**
     * This static void method gets the appointments of the logged in user that are within 15 minutes of logging in and
     * shows the alert message with appointment details.
     * @param passedAppointment Appointments
     */
    public static void displaySoonAppointments(Appointments passedAppointment) {
        Alert comingAppointment = new Alert(Alert.AlertType.INFORMATION);
        comingAppointment.setTitle(passedAppointment.getTitle());
        Long timeDifference = ChronoUnit.MINUTES.between(LocalDateTime.now(), passedAppointment.getStartTime());
        comingAppointment.setContentText("Appointment ID : " + passedAppointment.getAppointmentId() + "\nTitle : "
                + passedAppointment.getTitle() + "\nDescription : " + passedAppointment.getDescription() +
                "\nStarting in " + timeDifference + " minutes");
        comingAppointment.showAndWait();
    }

    /**
     * This static void method displays an ERROR alert message in English and French when login is unsuccessful.
     */
    public static void failedLoginError() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        ResourceBundle resBundle = ResourceBundle.getBundle("lang_fr", Locale.getDefault());
        if (Locale.getDefault().getLanguage().equals("fr")) {
            errorAlert.setTitle( getResBundle( resBundle ).getString("loginfailed"));
            errorAlert.setContentText( getResBundle( resBundle ).getString("checkcredentials"));
        } else {
            errorAlert.setTitle("Login Failed");
            errorAlert.setContentText("Please make sure the Username and Password are correct");
        }
        errorAlert.showAndWait();
    }

    /**
     * This is the resource bundle containing the language properties English and French.
     * @param resBundle ResourceBundle
     * @return resBundle dependent upon the user's location
     */
    public static ResourceBundle getResBundle(ResourceBundle resBundle) {
        return resBundle;
    }

    /**
     * Shows an alert saying the customer cannot be deleted because they have appointments and asks the users if they want to delete the appointments first.
     */
    public static Optional<ButtonType> customerHasAppointmentsError() {
        Alert deleteCustomersAppointmentAlert = new Alert(Alert.AlertType.CONFIRMATION);
        deleteCustomersAppointmentAlert.setTitle("Customer has appointments");
        deleteCustomersAppointmentAlert.setContentText("This customer has appointments and cannot be deleted. Would you like to delete all their appointments now?");
        Optional<ButtonType> result = deleteCustomersAppointmentAlert.showAndWait();
        return result;
    }

    /**
     * Displays alert confirming with the user if they really want to delete the customer or appointment.
     */
    public static Optional<ButtonType> deleteWarning() {
        Alert confirmDeleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDeleteAlert.setTitle("Confirm Delete");
        confirmDeleteAlert.setContentText("Are you sure you want to delete this?");
        Optional<ButtonType> result = confirmDeleteAlert.showAndWait();
        return result;
    }

    /**
     * This method displays saying the customer has been deleted
     * @param deletedCustomerId int ID of the customer deleted
     */
    public static void deleteSuccessfulWithoutAppointment(int deletedCustomerId) {
        Alert errorAlert = new Alert( Alert.AlertType.INFORMATION );
        errorAlert.setTitle( "Delete Successful" );
        errorAlert.setContentText( "The customer with Id " + deletedCustomerId + " has been deleted" );
        errorAlert.showAndWait();
    }

    /**
     * This method displays the message saying the appointment has been deleted.
     * @param deletedAppointmentId int the appointment ID that was deleted
     */
    public static void appointmentDeleteConfirmation(int deletedAppointmentId, String deleteAppointmentType) {
        Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
        errorAlert.setTitle("Delete Successful");
        errorAlert.setContentText("The appointment with Id " + deletedAppointmentId + "\nAppointment type: " +
                deleteAppointmentType + " has been deleted");
        errorAlert.showAndWait();
    }

    /**
     * This method shows the alert saying the associated appointments are deleted and the logged in user can delete
     * the customer.
     * @param deletedCustomerId int deleted customer's ID
     */
    public static void deleteAppointmentSuccessfulNowDeleteCustomer(int deletedCustomerId) {
        Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
        errorAlert.setTitle("Delete Successful");
        errorAlert.setContentText("All appointments for customer with id " + deletedCustomerId +
                " have been deleted. Please delete the customer now");
        errorAlert.showAndWait();
    }

    /**
     * Displays an alert error when attempting to click the update button without selecting an item first.
     */
    public static void nonSelectionErrorUpdate() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Nothing selected");
        errorAlert.setContentText("Please select an item to update");
        errorAlert.showAndWait();
    }

    /**
     * Displays an alert error when attempting to click the delete button without selecting an item first.
     */
    public static void nonSelectionErrorDelete() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Nothing selected");
        errorAlert.setContentText("Please select an item to delete");
        errorAlert.showAndWait();
    }

    /**
     * Displays an alert error when users attempts to schedule an appointment outside of the business ours.
     */
    public static void businessClosedError() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Business Closed");
        errorAlert.setContentText("Business is not open at this scheduled time. Please schedule between 08:00 and " +
                "22:00 EST.");
        errorAlert.showAndWait();
    }

    /**
     * Displays an alert error when users attempts to schedule an appointment that has a ending time before the
     * starting time.
     */
    public static void endTimeBeforeStartTimeError() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("End Time before Start Time");
        errorAlert.setContentText("End Time of Appointment cannot be set before the Start Time");
        errorAlert.showAndWait();
    }

    /**
     * This method triggers an error alert if the customer has overlapping appointments. Details of the overlapping
     * appointment is displayed.
     * @param passedAppointment Appointments
     */
    public static void appointmentForCustomersOverlap(Appointments passedAppointment) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Customer Appointment Overlap");
        errorAlert.setContentText("Customer " + passedAppointment.getCustomerName() + " already has another appointment " +
                "on that timeslot. Here are the details." + " \n Title : " + passedAppointment.getTitle() +
                "\n Description : " + passedAppointment.getDescription() + " \n Start Time : " +
                passedAppointment.getStartTime() + " \n End Time : " + passedAppointment.getEndTime());
        errorAlert.showAndWait();
    }

    /**
     * Displays an error when attempting to add appointment with some null values.
     */
    public static void errorAddingAppointment() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Error adding appointment");
        errorAlert.setContentText("Please make sure the fields are not empty and properly filled");
        errorAlert.showAndWait();
    }

    /**
     * Displays an error when attempting to add appointments or customers with some null values.
     */
    public static void nullValueEntry() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Null Entered");
        errorAlert.setContentText("Fields cannot be empty");
        errorAlert.showAndWait();
    }



}
