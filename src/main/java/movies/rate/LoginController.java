package movies.rate;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginController {

  @FXML
  private Button loginButton;
  @FXML
  private Button registerButton;
  @FXML
  private Button cancelButton;

  
  
  
  @FXML
  public void loginButtonAction(ActionEvent event){
    // TBD
  }
  
  @FXML
  public void registerButtonAction(ActionEvent event) throws IOException{
    
    // FXML laden
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/registration.fxml"));
    Parent root = fxmlLoader.load();
    
    // Stage für das neue Fenster setzen
    Stage registerStage = new Stage();
    registerStage.setTitle("Registrierung");
    registerStage.setScene(new Scene(root, 508, 354));

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

}
