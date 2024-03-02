package movies.rate.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
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
import movies.rate.services.LoginService;
import movies.rate.services.MediaService;

public class MediaController {

    private static final Logger LOG = Logger.getLogger(MediaController.class.getName());

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
        seriesNumberSeasonsColumn.setCellValueFactory(new PropertyValueFactory<>("nrOfSeasons"));
        seriesRatingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));

        // Daten zur TableView hinzufügen
        updateMovieList();
        updateSeriesList();
    }

    @FXML
    public void addMediumButtonAction(ActionEvent event) throws IOException {

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

    @FXML
    public void addToMyListButtonAction(ActionEvent event) {
        try {
            Movie selectedMovie = movieTableView.getItems().get(movieTableView.getSelectionModel().getSelectedIndex());
            if (selectedMovie != null) {
                LoginService.getInstance().getLoginUser().addToMyList(selectedMovie);
            }

        } catch (IndexOutOfBoundsException e) {
            LOG.info("Kein Film ausgewählt. Kann nicht zur Liste hinzugefügt werden.");
        }

        try {
            Series selectedSeries = seriesTableView.getItems()
                    .get(seriesTableView.getSelectionModel().getSelectedIndex());
            if (selectedSeries != null) {
                LoginService.getInstance().getLoginUser().addToMyList(selectedSeries);
            }
        } catch (IndexOutOfBoundsException e) {
            LOG.info("Keine Serie ausgewählt. Kann nicht zur Liste hinzugefügt werden.");
        }
    }

    // Filmliste aktualisieren
    public void updateMovieList() {
        movieTableView
                .setItems(FXCollections.observableArrayList(MediaService.getInstance().getMediaByType(Movie.class)));
    }

    // Serienliste aktualisieren
    public void updateSeriesList() {

        seriesTableView
                .setItems(FXCollections.observableArrayList(MediaService.getInstance().getMediaByType(Series.class)));
    }

}
