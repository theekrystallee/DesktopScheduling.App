package Controllers;

import DBAccess.DBAppointments;
import DBAccess.DBUsers;
import Database.DBConnection;
import Database.Localization;
import Database.LoginManager;
import Model.Appointments;
import Model.Users;
import javafx.collections.ObservableList;
import static javafx.fxml.FXMLLoader.load;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.TimeZone;

/**
 * This class handles the login form when the user runs opens the program. Initializes the Locale zone label, login
 * manager, authorizes login credentials and throws error messages if credentials are invalid.
 * @author Krystal Lee
 * @version 1.0
 * @since Summer 2021
 */
public class LoginFormController {

    @FXML
    private Label user;

    @FXML
    private Label password;

    @FXML
    private TextField passwordText;

    @FXML
    private TextField usernameText;

    @FXML
    private Button loginButton;

    @FXML
    private Button exitButton;

    @FXML
    public Label loginZoneLabel;


    @FXML
    public void initialize() {
        ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
        loginZoneLabel.setText(String.valueOf(localZoneId));
        ResourceBundle resBundle = ResourceBundle.getBundle("lang_fr", Locale.getDefault());
        //password.setText(resBundle.getString("login"));
        if (Locale.getDefault().getLanguage().equals("fr")) {
            user.setText( resBundle.getString( "username" ) );
            password.setText( resBundle.getString( "password" ) );
            loginButton.setText( resBundle.getString( "login" ) );
            exitButton.setText( resBundle.getString( "exit" ) );
        }
    }

    /**
     * This handles the LoginForm.fxml file. This logs all login events for reporting. Uses Lambda expression to check
     * that the session ID that is authorized isn't null for authentication.
     * @param loginManager LoginManager
     */
    @FXML
    public void initManager(final LoginManager loginManager) {
        loginButton.setOnAction( (ActionEvent event) -> {
            String sessionID = authorize();
            if (sessionID != null) {
                loginManager.authenticated( sessionID );
            }
        } );
    }

    /**
     * This String method authorizes the login credentials. Checks the database to see if the login credentials are
     * valid. If valid, the it generates a session ID and logs the activity. Else, no session ID is generated and
     * logs as null.
     * @return null
     */
    private String authorize() {

        if (DBConnection.validCredentials( user.getText(), password.getText() )) {
            LoginManager.processLog( true, user.getText() );
            return generateSessionID( user.getText(), "" );
        } else {
            LoginManager.processLog( false, user.getText() );
            invalidCredentials();
            return null;
        }

    }

    /**
     * This handles the invalid credentials error alert message. An error alert is triggered if invalid. Uses lambda
     * expressions to check if the OK button was clicked and re-initializes the program.
     */
    @FXML
    void invalidCredentials() {
        Localization locale = new Localization();
        Alert alert = new Alert( Alert.AlertType.ERROR );
        alert.setTitle( locale.getWarningTitleText() );
        alert.setHeaderText( locale.getWarningMessageText() );
        alert.showAndWait().filter( response -> response == ButtonType.OK ).ifPresent( (ButtonType response) -> {
            initialize(); // reinitialize program
                }
        );
    }


    private String generateSessionID(String userName, String userID) {
        return userName;
    }

    /**
     * This handles the login click mouse event. The login event is logged in login_activity.txt file.
     * @param event MouseEvent of the login clicks
     * @throws IOException exception is login in null
     */
    @FXML
    public void loginClicked(MouseEvent event) throws IOException {
        String usernameString = usernameText.getText().trim();
        String passwordString = passwordText.getText().trim();

        String fileName = "login_activity.txt";
        FileWriter fileWriter = new FileWriter( fileName, true );
        PrintWriter outputFile = new PrintWriter( fileWriter );

        Users loggedUser = DBUsers.loginUser( usernameString, passwordString );
        if (loggedUser == null) {
            AlertMessageController.failedLoginError();
            outputFile.println( "Unsuccessful login attempt by username: " + usernameString );
            outputFile.println( "Activity on: " + Instant.now() + "UTC." );
            outputFile.println( "999FAIL" );
            passwordText.clear();
        } else {
            Parent root = load( getClass().getResource( "/Views/MainMenu.fxml" ) );
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Scene scene = new Scene( root );
            stage.setTitle( "Welcome" );
            stage.setScene( scene );
            stage.show();
            outputFile.println( "Successful login by username: " + usernameString );
            outputFile.println( "Activity on: " + Instant.now() + " UTC." );
            outputFile.println( "111SUCCESS" );

            ObservableList<Appointments> appointmentsForLoggedInUserSoon = DBAppointments.getAppointmentsForLoggedInUser
                    ( loggedUser.getUserId() );
            if (appointmentsForLoggedInUserSoon.size() == 0) {
                //System.out.println("no appointments");
                AlertMessageController.userHasNoAppointmentsSoonAlert( loggedUser.getUserName() );
            } else {
                //System.out.println("you got appointments");
                Optional<ButtonType> answer = AlertMessageController.userHasAppointmentsSoonAlert( loggedUser.getUserName(),
                        appointmentsForLoggedInUserSoon.size(), appointmentsForLoggedInUserSoon.get( 0 ).getStartTime());
                if (answer.isPresent() && answer.get() == ButtonType.OK) {

                    for (Appointments element : appointmentsForLoggedInUserSoon) {
                        AlertMessageController.displaySoonAppointments( element );
                    }

                }
            }

        }
        outputFile.println( "------------------------------------------------------" );
        outputFile.close();
    }

    @FXML
    public void exitApplication(ActionEvent event) {
        System.exit( 0 );

    }
}

