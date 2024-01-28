package movies.rate;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LoginController {

  @FXML
  private Button loginButton;
  @FXML
  private Button registerButton;
  @FXML
  private Button cancelButton;

  @FXML
  public void cancelButtonAction(ActionEvent event){
    Stage stage = (Stage) cancelButton.getScene().getWindow();
    stage.close();
  }


    
  @FXML
  public void loginButtonAction(ActionEvent event){
    // TBD
  }

  @FXML
  public void registerButtonAction(ActionEvent event){
      // TBD
  }


}
