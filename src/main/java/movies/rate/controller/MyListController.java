package movies.rate.controller;

import java.util.Optional;
import java.util.logging.Logger;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import movies.rate.model.Media;
import movies.rate.services.LoginService;
import movies.rate.services.MediaService;

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
    myRatingColumn.setCellValueFactory(cellData -> {
      Media media = cellData.getValue();
      Double existingRating = LoginService.getInstance().getLoginUser().getRatings().get(media.getId());
      if(existingRating != null) {
        return new ReadOnlyStringWrapper(existingRating.toString());
      } else {
        return new ReadOnlyStringWrapper("-");
      }
    });
    myListTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        if(event.getClickCount() > 1) {
          try {
          Media clickedMedia = myListTable.getItems().get(myListTable.getSelectionModel().getSelectedIndex());

          if(clickedMedia != null) {
            Double existingRating = LoginService.getInstance().getLoginUser().getRatings().get(clickedMedia.getId());

            TextInputDialog td = new TextInputDialog(existingRating != null ? existingRating.toString() : "0");
            Optional<String> result = td.showAndWait();
            double newRating = Double.parseDouble(result.get());
            LoginService.getInstance().getLoginUser().getRatings().put(clickedMedia.getId(), newRating);
            if( existingRating != null) {
              clickedMedia.updateRating(existingRating, newRating);
            } else {
              clickedMedia.addRating(newRating);
            }

            MediaService.getInstance().updateMedia(clickedMedia);
            // myListTable.setItems(FXCollections.observableArrayList(LoginService.getInstance().getLoginUser().getMyList()));
            myListTable.refresh();

          }
          } catch (IndexOutOfBoundsException e) {
            LOG.info("Clicked Medium not found");
          }

        }
      }
    });

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
