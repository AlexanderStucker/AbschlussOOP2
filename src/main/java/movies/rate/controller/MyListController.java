package movies.rate.controller;

import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import movies.rate.model.Media;
import movies.rate.services.LoginService;

public class MyListController {

  private static final Logger LOG = Logger.getLogger(MyListController.class.getName());

  // Film TableView
  @FXML
  private TableView<Media> myListTable;
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
    releaseDateColumn.setCellValueFactory(new PropertyValueFactory<>("formattedReleaseDate"));
    genreColumn.setCellValueFactory(new PropertyValueFactory<>("genres"));
    myRatingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));

    myListTable.setItems(FXCollections.observableArrayList(LoginService.getInstance().getLoginUser().getMyList()));
  }

  @FXML
  public void removeFromMyListButtonAction(ActionEvent event) {
    try {
    Media selectedMedia = myListTable.getItems().get(myListTable.getSelectionModel().getSelectedIndex());
    if(selectedMedia != null) {
      LoginService.getInstance().getLoginUser().removeFromMyList(selectedMedia);
      myListTable.setItems(FXCollections.observableArrayList(LoginService.getInstance().getLoginUser().getMyList()));
    }
    } catch (IndexOutOfBoundsException e) {
      LOG.info("Kein Medium ausgewählt. Kann nich von Liste entfernt werden!");
    }
  }
}
