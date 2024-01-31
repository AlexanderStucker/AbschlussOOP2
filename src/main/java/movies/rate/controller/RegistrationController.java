package movies.rate.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import movies.rate.model.User;


public class RegistrationController {

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

      // Überprüfen ob der alle Felder ausgefüllt wurden, die entsprechende Nachricht wird ins Label geschrieben
      if(!username.isEmpty() && !password.isEmpty() && password.equals(confirmPassword)){
      User newUser = new User(username, password);

      saveUser(newUser);

      messageLabel.setText("User created");
      messageLabel.setVisible(true);

    } else if(username.isEmpty()){
      messageLabel.setText("No username selected");
      messageLabel.setVisible(true);

    } else{
      messageLabel.setText("Password does not match");
      messageLabel.setVisible(true);
    }
    // setzt nach 2 sekunden das Label wieder auf unsichtbar
    PauseTransition waitTime = new PauseTransition(Duration.seconds(2));
      waitTime.setOnFinished(e -> messageLabel.setVisible(false));
      waitTime.play();

  }
  
  
  @FXML
  public void cancelButtonAction(ActionEvent event) throws IOException{
    // Ganze Stage schliessen
    Stage stage = (Stage) cancelButton.getScene().getWindow();
    stage.close();
  }

  private void saveUser(User user){
    // Liste mit Usern für das File
    List<User> userList = new ArrayList<>();
    // File im Arbeitsverzeichnis speichern
    File file = new File("users.ser");
    // Überprüfen ob das file existiert. Wenn Ja, file deserialisieren und der Liste hinzufügen
    if (file.exists()){
      try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            userList = (List<User>) in.readObject(); 
          } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
          }
    }

    // Der neue User der Liste hinzufügen
    userList.add(user);

    // User.ser Datei wird per Outputstream serialisiert
    try (ObjectOutputStream newFile = new ObjectOutputStream(new FileOutputStream(file))){
     newFile.writeObject(userList);
    } catch(IOException e){
      e.printStackTrace();
    }
  }

}