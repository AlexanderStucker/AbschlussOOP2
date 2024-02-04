package movies.rate.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

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
  public void loginButtonAction(ActionEvent event) throws IOException{
    String username = usernameField.getText();
    String password = passwordField.getText();

    // Falls der User bekannt ist, ist Login i. O. / Falls nicht, login Failed
    if(userValidation(username, password)){
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/movie.fxml"));
    Parent root = fxmlLoader.load();


    // Der Username wird an den movieController weitergegeben
    movieController giveUsername = fxmlLoader.getController();
    giveUsername.setUsername(username);


    Stage movieStage = new Stage();
    movieStage.setTitle("CineRate");
    movieStage.setScene(new Scene(root, 1350, 900));
    movieStage.setResizable(false);
    
    movieStage.show();
  
  
  
  }else{
      wrongPasswordLabel.setText("Wrong password or username");
      wrongPasswordLabel.setVisible(true);

      // Nach 2 Sekunden wird das Label wieder auf unsichtbar gestellt
      PauseTransition waitTime = new PauseTransition(Duration.seconds(2));
      waitTime.setOnFinished(e -> wrongPasswordLabel.setVisible(false));
      waitTime.play();

    }
  }
  
  @FXML
  public void registerButtonAction(ActionEvent event) throws IOException{
    
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
  public void cancelButtonAction(ActionEvent event){
    // Ganze Stage schliessen
    Stage stage = (Stage) cancelButton.getScene().getWindow();
    stage.close();
  }


  // Methode um zu prüfen ob der User sich registriert hat
  private boolean userValidation(String username, String password){

    // Die Datei muss deserialisiert werden
    List <User> userList = readUsers();
    
    // Datei nach dem User durchsuchen
    for (User user : userList) {
      if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
        return true;
      }
    }
    return false;
  }

  // Methode um Users auszulesen
  private List<User> readUsers(){
    File file = new File("users.ser");

    // Datei deserialisieren und liste zurückgeben. 
    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
        return (List<User>) in.readObject();
    // IOException und ClassException. eine leere ArrayListe zurückgeben, falls eine Eception auftritt
      } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
        return new ArrayList<>();
      }
    }
}
