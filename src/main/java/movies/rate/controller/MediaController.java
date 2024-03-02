package movies.rate.controller;

import java.io.IOException;
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
import movies.rate.model.Series;
import movies.rate.model.enums.FSKRating;
import movies.rate.model.enums.Genre;


public class MediaController {
  
  @FXML
  private Button addButton;
  
  @FXML
  private Button addToMyListButton;
  
  @FXML
  private TableView<Movie> movieTableView;
  @FXML
  private TableColumn<Movie, String> movieTitleColumn;
  @FXML
  private TableColumn<Movie, String> movieDescriptionColumn;
  @FXML
  private TableColumn<Movie, String> movieReleaseDateColumn;
  @FXML
  private TableColumn<Movie, String> movieGenreColumn;
  @FXML
  private TableColumn<Movie, String> movieRuntimeColumn;
  @FXML
  private TableColumn<Movie, String> movieRatingColumn;
  
  @FXML
  private TableView<Series> seriesTableView;
  @FXML
  private TableColumn<Series, String> seriesTitleColumn;
  @FXML
  private TableColumn<Series, String> seriesDescriptionColumn;
  @FXML
  private TableColumn<Series, String> seriesReleaseDateColumn;
  @FXML
  private TableColumn<Series, String> seriesGenreColumn;
  @FXML
  private TableColumn<Series, String> seriesNumberSeasonsColumn;
  @FXML
  private TableColumn<Series, String> seriesRatingColumn;


@FXML
public void initialize() {
    // Spalten für die TableView definieren
    movieTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    movieDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
    movieReleaseDateColumn.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
    movieGenreColumn.setCellValueFactory(new PropertyValueFactory<>("genres"));
    movieRuntimeColumn.setCellValueFactory(new PropertyValueFactory<>("runtimeInMins"));
    movieRatingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));

    seriesTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    seriesDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
    seriesReleaseDateColumn.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
    seriesGenreColumn.setCellValueFactory(new PropertyValueFactory<>("genres"));
    seriesNumberSeasonsColumn.setCellValueFactory(new PropertyValueFactory<>("numberSeasons"));
    seriesRatingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
    
    // Daten zur TableView hinzufügen
    updateMovieList();
    updateSeriesList();
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

// Methode, um die Liste zu aktualisieren, ohne die gesamte Liste neu zu laden
public void updateSeriesList() {
    // Hol dir die aktuelle Liste der Serien in der TableView
    List<Series> currentSeries = seriesTableView.getItems();

    // Hol dir die Liste der Serien vom AddMediumController
    List<Series> newSeries = AddMediumController.getSeries();

    // Erstelle eine neue ObservableList für die TableView
    ObservableList<Series> seriesToUpdate = FXCollections.observableArrayList();

    // Füge alle aktuellen Serien hinzu
    seriesToUpdate.addAll(currentSeries);

    // Gehe durch die neuen Serien und füge sie hinzu, wenn sie noch nicht vorhanden sind
    for (Series series : newSeries) {
        if (!seriesToUpdate.contains(series)) {
            seriesToUpdate.add(series);
        }
    }

    // Setze die aktualisierte Liste als Items der TableView
    seriesTableView.setItems(seriesToUpdate);
}

}


