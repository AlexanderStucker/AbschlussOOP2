package movies.rate.controller;


import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import movies.rate.model.Movie;

public class MyListController {

  @FXML
  private TableView<Movie> myListTableView;

  @FXML
  private TableColumn<Movie, String> titleColumn;

  @FXML
  private TableColumn<Movie, String> descriptionColumn;

  @FXML
  private TableColumn<Movie, String> releaseDateColumn;

  @FXML
  private TableColumn<Movie, String> genreColumn;
  @FXML
  private TableColumn<Movie, String> runtimeColumn;
  @FXML
  private TableColumn<Movie, String> myRatingColumn;


@FXML
public void initialize() {
    // Spalten für die TableView definieren
    titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
    releaseDateColumn.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
    genreColumn.setCellValueFactory(new PropertyValueFactory<>("genres"));
    runtimeColumn.setCellValueFactory(new PropertyValueFactory<>("runtimeInMins"));
    myRatingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
}
      



}
