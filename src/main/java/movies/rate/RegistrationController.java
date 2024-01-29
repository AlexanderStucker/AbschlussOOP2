package movies.rate;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class RegistrationController {

  @FXML
  private Button registerButton;
  @FXML
  private Button cancelButton;



  
  @FXML
  public void confirmRegistration(){
    // TBD
  }
  
  
  @FXML
  public void cancelButtonAction(ActionEvent event) throws IOException{
    // Ganze Stage schliessen
    Stage stage = (Stage) cancelButton.getScene().getWindow();
    stage.close();
  }
  
  









}