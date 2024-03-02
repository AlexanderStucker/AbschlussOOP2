package movies.rate.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import movies.rate.model.User;
import movies.rate.services.UserService;

public class RegistrationController {

  private static final Logger LOG = Logger.getLogger(RegistrationController.class.getName()); 

  @FXML
  private TextField usernameField;
  @FXML
  private TextField passwordField;
  @FXML
  private TextField confirmPasswordField;
  @FXML
  private Label messageLabel;
  @FXML
  private Button registerButton;
  @FXML
  private Button cancelButton;

  @FXML
  public void confirmRegistration() {
    String username = usernameField.getText();
    String password = passwordField.getText();
    String confirmPassword = confirmPasswordField.getText();

    // Überprüfen ob der alle Felder ausgefüllt wurden, die entsprechende Nachricht
    // wird ins Label geschrieben
    if (!username.isEmpty() && !password.isEmpty() && password.equals(confirmPassword)) {
      User newUser = new User(username, password);

      try {
      UserService.getInstance().addUser(newUser);
      messageLabel.setText("User created");
      messageLabel.setVisible(true);
      } catch (IllegalArgumentException e) {
        LOG.severe("Could not create user, user already exists");
        messageLabel.setText("User already exists");
        messageLabel.setVisible(true);
      }

    } else if (username.isEmpty()) {
      messageLabel.setText("No username selected");
      messageLabel.setVisible(true);

    } else {
      messageLabel.setText("Password does not match");
      messageLabel.setVisible(true);
    }
    // setzt nach 2 sekunden das Label wieder auf unsichtbar
    PauseTransition waitTime = new PauseTransition(Duration.seconds(2));
    waitTime.setOnFinished(e -> messageLabel.setVisible(false));
    waitTime.play();

  }

  @FXML
  public void cancelButtonAction(ActionEvent event) throws IOException {
    // Ganze Stage schliessen
    Stage stage = (Stage) cancelButton.getScene().getWindow();
    stage.close();
  }
}