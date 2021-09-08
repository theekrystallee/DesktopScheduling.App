package Controllers;

import javafx.scene.input.MouseEvent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * This controller class that handles MainMenu.fxml file.
 * @author Krystal Lee
 * @version 1.0
 * @since Summer 2021
 */
public class MainMenuController {

    /**
     * This void method handles the home button and sends users to the main screen.
     */
    @FXML
    public void navigateToMainScreen(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Views/MainMenu.fxml"));
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 900, 600);
            stage.setTitle("Main Screen");
            stage.setScene(scene);
            stage.show();
        } catch (NullPointerException e) {
            System.out.print(e);
        }
    }

    /**
     * This void method handles the Customers button and switches the window to Customers Screen.
     */
    @FXML
    public void navigateToCustomers(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource( "/Views/CustomersScreen.fxml" ));
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 900, 600);
        stage.setTitle("Customers");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This void method handles the Appointments button and switches to Appointments screen.
     */
    @FXML
    public void navigateToAppointments(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/AppointmentsScreen.fxml"));
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Appointments");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This void method handles the Reports button and switches to Reports screen.
     */
    @FXML
    public void navigateToReports(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/ReportsScreen.fxml"));
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Reports");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This void method handles the exit button and quits the application.
     */
    @FXML
    public void handleExit() {
        System.exit(0);
    }

}
