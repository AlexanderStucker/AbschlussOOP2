package movies.rate.model;

import java.util.Date;
import java.util.List;

import movies.rate.model.enums.FSKRating;
import movies.rate.model.enums.Genre;

public class Movie extends Media {
    
    private static final long serialVersionUID = 1L;

    private int runtimeInMins;

    public Movie(String title, String description, Date releaseDate, List<Genre> genres, FSKRating fskRating, int runtimeInMins) {
        super(title, description, releaseDate, genres, fskRating);
        this.runtimeInMins = runtimeInMins;
    }


    public int getRuntimeInMins() {
        return this.runtimeInMins;
    }

    public void setRuntimeInMins(int runtimeInMins) {
        this.runtimeInMins = runtimeInMins;
    }
}
