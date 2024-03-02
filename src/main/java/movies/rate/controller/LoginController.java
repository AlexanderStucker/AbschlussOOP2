package movies.rate.controller;

import java.io.IOException;
import java.util.NoSuchElementException;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import movies.rate.model.User;
import movies.rate.services.LoginService;

public class LoginController {

  @FXML
  private TextField usernameField;
  @FXML
  private TextField passwordField;
  @FXML
  private Label wrongPasswordLabel;
  @FXML
  private Button loginButton;
  @FXML
  private Button registerButton;
  @FXML
  private Button cancelButton;

  @FXML
  public void loginButtonAction(ActionEvent event) throws IOException {
    String username = usernameField.getText();
    String password = passwordField.getText();

    try {
      User loginUser = LoginService.getInstance().login(username, password);

      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/sidebar.fxml"));
      Parent root = fxmlLoader.load();

      // Der Username wird an den movieController weitergegeben
      sidebarController giveUsername = fxmlLoader.getController();
      giveUsername.setUsername(loginUser.getUsername());

      // Stage für das neue Fenster eröffnen
      Stage mainStage = (Stage) usernameField.getScene().getWindow();
      mainStage.setTitle("CineRate");
      mainStage.setScene(new Scene(root, 1350, 900));
      mainStage.setResizable(false);

      // Die Movie-Stage anzeigen
      mainStage.show();

    } catch (IllegalArgumentException ex) {
      wrongPasswordLabel.setText("Wrong password or username!");
      wrongPasswordLabel.setVisible(true);

    } catch (NoSuchElementException e) {
      wrongPasswordLabel.setText("User does not exist!");
      wrongPasswordLabel.setVisible(true);
    }

    // Nach 2 Sekunden wird das Label wieder auf unsichtbar gestellt
    PauseTransition waitTime = new PauseTransition(Duration.seconds(2));
    waitTime.setOnFinished(e -> wrongPasswordLabel.setVisible(false));
    waitTime.play();
  }

  @FXML
  public void registerButtonAction(ActionEvent event) throws IOException {

    // FXML laden
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/registration.fxml"));
    Parent root = fxmlLoader.load();

    // Stage für das neue Fenster setzen
    Stage registerStage = new Stage();
    registerStage.setTitle("Registrierung");
    registerStage.setScene(new Scene(root, 520, 396));
    registerStage.setResizable(false);

    // Das Login-Fenster unklickbar machen
    // Login als Elternfenster referenzieren
    Stage loginStage = (Stage) registerButton.getScene().getWindow();

    // Modalität des Registrationsfensters setzen
    registerStage.initModality(Modality.WINDOW_MODAL);
    registerStage.initOwner(loginStage);

    registerStage.show();
  }

  @FXML
  public void cancelButtonAction(ActionEvent event) {
    // Ganze Stage schliessen
    Stage stage = (Stage) cancelButton.getScene().getWindow();
    stage.close();
  }
}
