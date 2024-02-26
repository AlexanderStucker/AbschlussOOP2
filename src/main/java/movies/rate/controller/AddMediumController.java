package movies.rate.controller;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import movies.rate.model.Movie;
import movies.rate.model.enums.FSKRating;
import movies.rate.model.enums.Genre;

import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AddMediumController {
    @FXML 
    private TextField titleField;
    @FXML 
    private TextArea descriptionField;
    @FXML 
    private DatePicker releaseDatePicker;
    @FXML 
    private ComboBox<String> genreBox;
    @FXML 
    private ComboBox<String> fskRatingBox;
    @FXML 
    private TextField runtimeField;
    @FXML 
    private TextField ratingField;

    private MyListController myListController;

    private static ObservableList<Movie> movies = FXCollections.observableArrayList();

    @FXML
    protected void initialize() {
        // Initialisierung der Comboboxen für Genre und fskRatings
      genreBox.getItems().addAll(Arrays.stream(Genre.values())
          .map(Enum::name)
          .collect(Collectors.toList()));
      fskRatingBox.getItems().addAll(Arrays.stream(FSKRating.values())
          .map(Enum::name)
          .collect(Collectors.toList()));
          
      // Listener, dass die movie Liste angeziegt wird
      movies.addListener((ListChangeListener<Movie>) change -> {
         if (myListController != null) {
            myListController.updateMovieList();
            }
        });
        
    }

    @FXML
    private void saveMovie() {
        String title = titleField.getText();
        String description = descriptionField.getText();
        Date releaseDate = Date.from(releaseDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<Genre> genres = List.of(Genre.valueOf(genreBox.getValue()));
        FSKRating fskRating = FSKRating.valueOf(fskRatingBox.getValue());
        int runtimeInMins = Integer.parseInt(runtimeField.getText());
        double rating = Double.parseDouble(ratingField.getText());

        Movie movie = new Movie(title, description, releaseDate, genres, fskRating, runtimeInMins, rating);
        movies.add(movie);

    }

    public void setMyListController(MyListController myListController) {
    this.myListController = myListController;
    }

    public static List<Movie> getMovies() {
        return movies;
    }
}
