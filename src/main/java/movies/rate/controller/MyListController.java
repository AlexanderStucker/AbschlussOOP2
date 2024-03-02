package movies.rate.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import movies.rate.model.Media;

public class MyListController {

  // Film TableView
  @FXML
  private TableView<Media> myListMovieTable;
  @FXML
  private TableColumn<Media, String> titleColumn;
  @FXML
  private TableColumn<Media, String> descriptionColumn;
  @FXML
  private TableColumn<Media, String> releaseDateColumn;
  @FXML
  private TableColumn<Media, String> genreColumn;
  @FXML
  private TableColumn<Media, String> myRatingColumn;

  @FXML
  public void initialize() {
    // Spalten für die Film TableView definieren
    titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
    releaseDateColumn.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
    genreColumn.setCellValueFactory(new PropertyValueFactory<>("genres"));
    myRatingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
  }
}
