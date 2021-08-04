package Controllers;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import DBAccess.DBAppointments;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import Model.*;
import Model.Appointments;

/**
 * This public class MonthTypeReportsScreenController handles all of the alerts. Controller class handles the
 * AlertMessage.fxml file. Controller class that handles MonthTypeReportScreen.fxml file.
 * @author Krystal Lee
 * @version 1.0
 * @since Summer 2021
 */
public class MonthTypeReportScreenController {

    @FXML
    private TableView<Appointments> monthTypeReportTable;

    @FXML
    private TableColumn<Appointments, String> monthColReport;

    @FXML
    private TableColumn<Appointments, String> typeColReport;

    @FXML
    private TableColumn<Appointments, Integer> occurrencesColReport;

    /**
     * This void method initializes the table with data grouped by month and type.
     */
    @FXML
    public void initialize() {

        try {
            monthTypeReportTable.setItems(DBAppointments.getAppointmentsByTypeAndMonth());
            monthColReport.setCellValueFactory(new PropertyValueFactory<>("month"));
            typeColReport.setCellValueFactory(new PropertyValueFactory<>("type"));
            occurrencesColReport.setCellValueFactory(new PropertyValueFactory<>("occurrences"));
            monthTypeReportTable.getSortOrder().add(monthColReport);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @FXML
    public void loadMonthTypeReportTable() {
        monthTypeReportTable.setItems(DBAppointments.getAppointmentsByTypeAndMonth());
        monthColReport.setCellValueFactory(new PropertyValueFactory<>("month"));
        typeColReport.setCellValueFactory(new PropertyValueFactory<>("type"));
        occurrencesColReport.setCellValueFactory(new PropertyValueFactory<>("occurrences"));
        monthTypeReportTable.getSortOrder().add(monthColReport);
    }

    @FXML
    public void populateReportByMonthType() {
        monthTypeReportTable.setItems(DBAppointments.getAppointmentsByTypeAndMonth());
        monthColReport.setCellValueFactory(new PropertyValueFactory<>("month"));
        typeColReport.setCellValueFactory(new PropertyValueFactory<>("type"));
        occurrencesColReport.setCellValueFactory(new PropertyValueFactory<>("occurrences"));
        monthTypeReportTable.getSortOrder().add(monthColReport);
    }


    @FXML
    public static ObservableList<Appointments> appointments(int selectedContact) {
        ObservableList<Appointments> monthTypeReportScreenController;
        monthTypeReportScreenController = DBAppointments.getAppointmentScheduleForContact( selectedContact );
        return monthTypeReportScreenController;
    }


}
