package movies.rate.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class sidebarController implements Initializable {


  @FXML
  private Button myListButton;
  @FXML
  private Button movieButton;
  @FXML
  private Button suggestionButton;
  @FXML
  private Label usernameLabel;
  @FXML
  private BorderPane borderPane;
  @FXML
  private AnchorPane anchorPaneMiddle;

  // Verwendung von initialize, damit die erste Seite korrekte angezeigt wird. Für die Aktion der Page-Wechsel verwenden wir Lambdas
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // MyList wird als erstes geladen
    loadPage("myList");

    // Lambdas für den wechsel der Pages
    myListButton.setOnAction(e -> loadPage("myList"));
    movieButton.setOnAction(e -> loadPage("movie"));
    suggestionButton.setOnAction(e -> loadPage("suggestions"));

  }

  // Username in der rechten Ecke anzeigen
  public void setUsername(String username){
    usernameLabel.setText(username);
  }

  private void loadPage(String page){
    try {
      Parent root = FXMLLoader.load(getClass().getResource("/fxml/" + page + ".fxml"));
      // Setzt die geladene Page in das Center der borderPane 
      borderPane.setCenter(root);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }



  
}
