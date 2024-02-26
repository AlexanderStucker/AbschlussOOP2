package movies.rate.model;

import java.util.Date;
import java.util.List;

import movies.rate.model.enums.FSKRating;
import movies.rate.model.enums.Genre;

public class Movie extends Media {
    
    private static final long serialVersionUID = 1L;

    private int runtimeInMins;
    private double rating;

    public Movie(String title, String description, Date releaseDate, List<Genre> genres, FSKRating fskRating, int runtimeInMins, double rating) {
        super(title, description, releaseDate, genres, fskRating);
        this.runtimeInMins = runtimeInMins;
        this.rating = rating;
    }


    public int getRuntimeInMins() {
        return this.runtimeInMins;
    }

    public void setRuntimeInMins(int runtimeInMins) {
        this.runtimeInMins = runtimeInMins;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public double getRating() {
        return rating;
    }


}
