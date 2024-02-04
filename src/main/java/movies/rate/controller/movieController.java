package movies.rate.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class movieController {


  @FXML
  private Button movieButton;

  @FXML
  private Button showsButton;

  @FXML
  private Button suggestionButton;

  @FXML
  private Label usernameLabel;



  // Username in der rechten Ecke anzeigen
  public void setUsername(String username){
    usernameLabel.setText(username);
  }

  
}
