package movies.rate.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import movies.rate.model.User;


public class RegistrationController {

  @FXML
  private TextField usernameField;
  @FXML
  private TextField passwordField;
  @FXML
  private Button registerButton;
  @FXML
  private Button cancelButton;


  @FXML
  public void confirmRegistration() {
    String username = usernameField.getText();
    String password = passwordField.getText();

    User newUser = new User(username, password);

    saveUser(newUser);
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