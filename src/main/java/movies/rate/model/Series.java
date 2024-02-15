package movies.rate.model;

import java.util.Date;
import java.util.List;

import movies.rate.model.enums.FSKRating;
import movies.rate.model.enums.Genre;

public class Series extends Media{
    
    private static final long serialVersionUID = 1L;

    private List<Season> seasons;
    
    public Series(String title, String description, Date releaseDate, List<Genre> genres, FSKRating fskRating, List<Season> seasons) {
        super(title, description, releaseDate, genres, fskRating);
        this.seasons = seasons;
    }

    /**
     * Return the average rating of all seasons
     */
    @Override
    public double getRating() {
        return seasons.stream().mapToDouble(Season::getRating).average().orElse(0);
    }

}
