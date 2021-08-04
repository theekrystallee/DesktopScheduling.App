package Main;

import javafx.collections.ObservableList;
import Model.Appointments;

public interface InterfaceAppointmentForContacts {

    /**
     * This declaration returns an ObservableList of appointments and takes in an integer as parameter.
     */
    ObservableList<Appointments> appointments(int contactID);
}