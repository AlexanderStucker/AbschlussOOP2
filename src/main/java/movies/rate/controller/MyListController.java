package movies.rate.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import movies.rate.model.Movie;

public class MyListController {

  @FXML
  private Button addButton;

  @FXML
  private ListView<Movie> movieListView;

  @FXML
  public void initialize() {
  // Darstellung der Filme bestimmen
  movieListView.setCellFactory(lv -> new ListCell<Movie>() {
      @Override
      protected void updateItem(Movie movie, boolean empty) {
          super.updateItem(movie, empty);
          if (empty || movie == null) {
              setText(null);
          } else {
              setText(movie.getTitle() + " - Rating: " + movie.getRating());
          }
      }
    });
  }


  @FXML
  public void addMediumButtonAction(ActionEvent event) throws IOException{
    
    // FXML laden
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/addMedium.fxml"));
    Parent root = fxmlLoader.load();


    // Controller setzten, damit die korrekte Liste aktuallisiert wird
    AddMediumController addMediumController = fxmlLoader.getController();
    addMediumController.setMyListController(this);
    
    // Stage für das neue Fenster setzen
    Stage addMediumStage = new Stage();
    addMediumStage.setTitle("Add a new Entry");
    addMediumStage.setScene(new Scene(root, 520, 620));
    addMediumStage.setResizable(false);



    // Das Login-Fenster unklickbar machen
    // Login als Elternfenster referenzieren
    Stage currentStage = (Stage) addButton.getScene().getWindow();

    // Modalität des Registrationsfensters setzen
    addMediumStage.initModality(Modality.WINDOW_MODAL);
    addMediumStage.initOwner(currentStage);
    
    addMediumStage.show();
  }

  // Methode für den Listener
  public void updateMovieList() {
    movieListView.getItems().clear();
    movieListView.getItems().addAll(AddMediumController.getMovies());
}



  
}
