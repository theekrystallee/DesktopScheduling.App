package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import Model.Appointments;

import java.awt.*;
import java.io.IOException;

/**
 * This public class ReportsScreenController handles all of the alerts. Controller class handles the ReportsScreen.fxml
 * file.
 * @author Krystal Lee
 * @version 1.0
 * @since Summer 2021
 */
public class ReportsScreenController {

    /**
     * This void method opens the month and type report screen.
     * @throws IOException throws
     */
    @FXML
    public void appointmentsReportButtonClicked() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/MonthTypeReportScreen.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Report by Month and Type");
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * This void method opens the schedule for contacts report screen.
     * @throws IOException throws
     */
    @FXML
    public void scheduleForContactsReportClicked() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/ScheduleContacts.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Schedule report for Contacts");
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * This void method opens the login information report screen.
     * @throws IOException throws
     */
    @FXML
    public void loginInformationButtonClicked() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/LoginInformationReport.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Add New Customer Form");
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * This void method handles the home button and navigates to the main screen.
     * @param event MouseEvent
     * @throws IOException throws
     */
    @FXML
    public void navigateToMainScreen(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/MainMenu.fxml"));
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This void method handles the exit button and closes the application.
     */
    @FXML
    public void handleExit() {
        System.exit(0);
    }
}
