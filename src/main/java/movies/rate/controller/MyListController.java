package movies.rate.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import movies.rate.model.Movie;
import movies.rate.model.enums.FSKRating;
import movies.rate.model.enums.Genre;

public class MyListController {

  @FXML
  private Button addButton;

  @FXML
  private TableView<Movie> movieTableView;

@FXML
public void initialize() {
    // Spalten für die TableView definieren
    TableColumn<Movie, String> titleColumn = new TableColumn<>("Title");
    titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    
    TableColumn<Movie, String> descriptionColumn = new TableColumn<>("Description");
    descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
    
    TableColumn<Movie, Date> releaseDateColumn = new TableColumn<>("Release Date");
    releaseDateColumn.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
    
    TableColumn<Movie, List<Genre>> genreColumn = new TableColumn<>("Genre");
    genreColumn.setCellValueFactory(new PropertyValueFactory<>("genres")); // Beachte, dass dies komplexer ist und möglicherweise angepasst werden muss
    
    TableColumn<Movie, FSKRating> fskRatingColumn = new TableColumn<>("FSK Rating");
    fskRatingColumn.setCellValueFactory(new PropertyValueFactory<>("fskRating"));
    
    TableColumn<Movie, Integer> runtimeColumn = new TableColumn<>("Runtime");
    runtimeColumn.setCellValueFactory(new PropertyValueFactory<>("runtimeInMins"));
    
    TableColumn<Movie, Double> ratingColumn = new TableColumn<>("My Rating");
    ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));

    // Spalten zur TableView hinzufügen
    movieTableView.getColumns().addAll(titleColumn, descriptionColumn, releaseDateColumn, genreColumn, fskRatingColumn, runtimeColumn, ratingColumn);

    // Daten zur TableView hinzufügen
    updateMovieList();
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

// Methode, um die Liste zu aktualisieren, ohne die gesamte Liste neu zu laden
public void updateMovieList() {
    // Hol dir die aktuelle Liste der Filme in der TableView
    List<Movie> currentMovies = movieTableView.getItems();

    // Hol dir die Liste der Filme vom AddMediumController
    List<Movie> newMovies = AddMediumController.getMovies();

    // Erstelle eine neue ObservableList für die TableView
    ObservableList<Movie> moviesToUpdate = FXCollections.observableArrayList();

    // Füge alle aktuellen Filme hinzu
    moviesToUpdate.addAll(currentMovies);

    // Gehe durch die neuen Filme und füge sie hinzu, wenn sie noch nicht vorhanden sind
    for (Movie newMovie : newMovies) {
        if (!moviesToUpdate.contains(newMovie)) {
            moviesToUpdate.add(newMovie);
        }
    }

    // Setze die aktualisierte Liste als Items der TableView
    movieTableView.setItems(moviesToUpdate);
}


}
