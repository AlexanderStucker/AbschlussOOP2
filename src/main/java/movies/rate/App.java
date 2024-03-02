package movies.rate;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import movies.rate.services.MediaService;
import movies.rate.services.UserService;

/**
 * JavaFX App
 */
public class App extends Application {

    private static final Logger LOG = Logger.getLogger(App.class.getName());

    private static Scene scene;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"), 520, 400);
        stage.setTitle("CineRate - Login");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        // Load Data
        try {
            UserService.getInstance().deserialize();
            MediaService.getInstance().deserialize();
        } catch (ClassNotFoundException e) {
            LOG.severe("Error while deserializing Data");
        }

        // Set Eventhandler for closeRequest to serialize all the data
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent e) {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Applikation schliessen");
                alert.setHeaderText("Möchten sie die Applikation wirklich schliessen?");
                alert.setContentText(
                        "Die automatische Speicherung hat nicht geklappt. Wenn sie die Applikation trotzdem schliessen, können Daten verloren gehen!");
                ButtonType btRetry = new ButtonType("Erneut versuchen");
                ButtonType btClose = new ButtonType("Schliessen");

                alert.getButtonTypes().setAll(btRetry, btClose);

                boolean retrying = false;

                // Try to serialize, if it fails ask to retry
                do {
                    try {
                        UserService.getInstance().serialize();
                        MediaService.getInstance().serialize();
                    } catch (Exception ex) {
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == btRetry) {
                            retrying = true;
                        } else {
                            retrying = false;
                        }
                    }
                } while (retrying);
            }
        });
    }

    static void setSceneRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

}