package movies.rate.controller;

import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.util.Duration;
import movies.rate.model.Movie;
import movies.rate.model.Series;
import movies.rate.model.enums.FSKRating;
import movies.rate.model.enums.Genre;
import movies.rate.services.LoginService;
import movies.rate.services.MediaService;

public class AddMediumController {

    @FXML
    private TextField titleFieldMovies;
    @FXML
    private TextField titleFieldSeasons;
    @FXML
    private TextArea descriptionFieldMovies;
    @FXML
    private TextArea descriptionFieldSeasons;
    @FXML
    private DatePicker releaseDatePickerMovies;
    @FXML
    private DatePicker releaseDatePickerSeasons;
    @FXML
    private ComboBox<String> genreBoxMovies;
    @FXML
    private ComboBox<String> fskRatingBoxSeasons;
    @FXML
    private ComboBox<String> genreBoxSeasons;
    @FXML
    private ComboBox<String> fskRatingBoxMovies;
    @FXML
    private TextField ratingFieldMovies;
    @FXML
    private TextField ratingFieldSeasons;

    @FXML
    private TextField runtimeField;
    @FXML
    private TextField numberOfSeasonsField;

    @FXML
    private Text newMediaErrorLabel;

    private MediaController myListController;

    @FXML
    protected void initialize() {
        // Initialisierung der Comboboxen für Genre und fskRatings
        genreBoxMovies.getItems().addAll(Arrays.stream(Genre.values())
                .map(Enum::name)
                .collect(Collectors.toList()));
        fskRatingBoxMovies.getItems().addAll(Arrays.stream(FSKRating.values())
                .map(Enum::name)
                .collect(Collectors.toList()));
        genreBoxSeasons.getItems().addAll(Arrays.stream(Genre.values())
                .map(Enum::name)
                .collect(Collectors.toList()));
        fskRatingBoxSeasons.getItems().addAll(Arrays.stream(FSKRating.values())
                .map(Enum::name)
                .collect(Collectors.toList()));

    }

    @FXML
    private void saveMovie() {
        try {
            String title = titleFieldMovies.getText();
            String description = descriptionFieldMovies.getText();
            Date releaseDate = Date
                    .from(releaseDatePickerMovies.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            List<Genre> genres = List.of(Genre.valueOf(genreBoxMovies.getValue()));
            FSKRating fskRating = FSKRating.valueOf(fskRatingBoxMovies.getValue());
            int runtimeInMins = Integer.parseInt(runtimeField.getText());
            double rating = Double.parseDouble(ratingFieldMovies.getText());

            Movie movie = new Movie(title, description, releaseDate, genres, fskRating, runtimeInMins);
            movie.addRating(rating);
            LoginService.getInstance().getLoginUser().getRatings().put(movie.getId(), rating);
            try {
                MediaService.getInstance().addMedia(movie);
            } catch (IllegalArgumentException ex) {
                newMediaErrorLabel.setText("Movie already exists");
                PauseTransition waitTime = new PauseTransition(Duration.seconds(2));
                waitTime.setOnFinished(e -> newMediaErrorLabel.setVisible(false));
                waitTime.play();
            }

            myListController.updateMovieList();
            newMediaErrorLabel.setText("Movie added");
            PauseTransition waitTime = new PauseTransition(Duration.seconds(2));
            waitTime.setOnFinished(e -> newMediaErrorLabel.setVisible(false));
            waitTime.play();
        } catch (NumberFormatException ex) {
            newMediaErrorLabel.setText("Form invalid");
            PauseTransition waitTime = new PauseTransition(Duration.seconds(2));
            waitTime.setOnFinished(e -> newMediaErrorLabel.setVisible(false));
            waitTime.play();
        }
    }

    @FXML
    private void saveSeries() {
        try {

            String title = titleFieldSeasons.getText();
            String description = descriptionFieldSeasons.getText();
            Date releaseDate = Date
                    .from(releaseDatePickerSeasons.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            List<Genre> genres = List.of(Genre.valueOf(genreBoxSeasons.getValue()));
            FSKRating fskRating = FSKRating.valueOf(fskRatingBoxSeasons.getValue());
            int nrOfSeasons = Integer.parseInt(numberOfSeasonsField.getText());
            double rating = Double.parseDouble(ratingFieldSeasons.getText());

            Series newSeries = new Series(title, description, releaseDate, genres, fskRating, nrOfSeasons);
            LoginService.getInstance().getLoginUser().getRatings().put(newSeries.getId(), rating);
            newSeries.addRating(rating);
            try {
                MediaService.getInstance().addMedia(newSeries);
            } catch (IllegalArgumentException ex) {
                newMediaErrorLabel.setText("Series already exists");
                // Nach 2 Sekunden wird das Label wieder auf unsichtbar gestellt
                PauseTransition waitTime = new PauseTransition(Duration.seconds(2));
                waitTime.setOnFinished(e -> newMediaErrorLabel.setVisible(false));
                waitTime.play();
            }
            myListController.updateSeriesList();
            newMediaErrorLabel.setText("Series added");
            PauseTransition waitTime = new PauseTransition(Duration.seconds(2));
            waitTime.setOnFinished(e -> newMediaErrorLabel.setVisible(false));
            waitTime.play();
        } catch (NumberFormatException ex) {
            newMediaErrorLabel.setText("Form invalid");
            PauseTransition waitTime = new PauseTransition(Duration.seconds(2));
            waitTime.setOnFinished(e -> newMediaErrorLabel.setVisible(false));
            waitTime.play();
        }
    }

    public void setMyListController(MediaController myListController) {
        this.myListController = myListController;
    }
}
