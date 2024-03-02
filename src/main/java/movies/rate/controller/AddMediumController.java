package movies.rate.controller;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import movies.rate.model.Movie;
import movies.rate.model.Season;
import movies.rate.model.Series;
import movies.rate.model.enums.FSKRating;
import movies.rate.model.enums.Genre;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    private MediaController myListController;

    private static ObservableList<Movie> movies = FXCollections.observableArrayList();
    private static ObservableList<Series> series = FXCollections.observableArrayList();

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
          
      // Listener, dass die movie Liste angeziegt wird
      movies.addListener((ListChangeListener<Movie>) change -> {
         if (myListController != null) {
            myListController.updateMovieList();
            }
        });

    series.addListener((ListChangeListener<Series>) change -> {
         if (myListController != null) {
            myListController.updateSeriesList();
            }
        });
        
    }

    @FXML
    private void saveMovie() {
        String title = titleFieldMovies.getText();
        String description = descriptionFieldMovies.getText();
        Date releaseDate = Date.from(releaseDatePickerMovies.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<Genre> genres = List.of(Genre.valueOf(genreBoxMovies.getValue()));
        FSKRating fskRating = FSKRating.valueOf(fskRatingBoxMovies.getValue());
        int runtimeInMins = Integer.parseInt(runtimeField.getText());
        double rating = Double.parseDouble(ratingFieldMovies.getText());

        Movie movie = new Movie(title, description, releaseDate, genres, fskRating, runtimeInMins, rating);
        movies.add(movie);

    }

    @FXML
    private void saveSeries() {
        String title = titleFieldSeasons.getText();
        String description = descriptionFieldSeasons.getText();
        Date releaseDate = Date.from(releaseDatePickerSeasons.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<Genre> genres = List.of(Genre.valueOf(genreBoxSeasons.getValue()));
        FSKRating fskRating = FSKRating.valueOf(fskRatingBoxSeasons.getValue());
        List<Season> seasons = new ArrayList<>();

        Series newSeries = new Series(title, description, releaseDate, genres, fskRating, seasons);
        series.add(newSeries);
    }

    public void setMyListController(MediaController myListController) {
    this.myListController = myListController;
    }

    public static List<Movie> getMovies() {
        return movies;
    }

    public static List<Series> getSeries(){
        return series;
    }
}
