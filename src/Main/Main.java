package Main;

import Database.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.SQLException;
import java.lang.*;

/**
 * The Main class that extends Application and runs and loads the the desktop scheduling application.
 * @author Krystal Lee
 * @version 1.0
 * @since Summer 2021
 */
public class Main extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/LoginForm.fxml"));
        primaryStage.setTitle("Scheduling Application");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.resizableProperty().setValue(false);
    }


    public static void main(String[] args) {
        try {
            DBConnection.startConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        launch(args);
        DBConnection.closeConnection();
    }
}
