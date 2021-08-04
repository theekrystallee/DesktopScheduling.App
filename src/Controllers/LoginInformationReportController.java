package Controllers;

import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * This public Controller class LoginInformationReportController handles the LoginInformationReport.fxml file.
 * LoginInformationReport.fxml file.
 * @author Krystal Lee
 * @version 1.0
 * @since Summer 2021
 */
public class LoginInformationReportController {

    @FXML
    private Label successfulLoginsLabel;

    @FXML
    private Label failedLoginsLabel;

    /**
     * This methos initialized the login information report screen through the login_activity.txt file. It counts how
     * successful and unsuccessful login attempts.
     * @throws IOException throws if login fails
     */
    @FXML
    public void initialize() throws IOException {
        String filename = "login_activity.txt";
        File fileToRead = new File(filename);
        if (fileToRead.exists()) {
            Scanner scannedFile = new Scanner(fileToRead);
            int successfulAttempts = 0;
            int failedAttempts = 0;

            while (scannedFile.hasNext()) {
                String item = scannedFile.nextLine();
                if (item.equals("111SUCCESS")) {
                    successfulAttempts++;
                }
                if (item.equals("999FAIL")) {
                    failedAttempts++;
                }
            }
            scannedFile.close();

            successfulLoginsLabel.setText(String.valueOf(successfulAttempts));
            failedLoginsLabel.setText(String.valueOf(failedAttempts));
        } else {
            System.out.println("file does not exist, please quit");
        }
    }
}
