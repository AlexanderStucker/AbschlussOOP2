package movies.rate.model;

import java.util.Date;
import java.util.List;

import movies.rate.model.enums.FSKRating;
import movies.rate.model.enums.Genre;

public class Series extends Media{
    
    private static final long serialVersionUID = 1L;
    private int nrOfSeasons;
    
    public Series(String title, String description, Date releaseDate, List<Genre> genres, FSKRating fskRating, int nrOfSeasons) {
        super(title, description, releaseDate, genres, fskRating);
        this.nrOfSeasons = nrOfSeasons;
    }


    public int getNrOfSeasons() {
        return this.nrOfSeasons;
    }

    public void setNrOfSeasons(int nrOfSeasons) {
        this.nrOfSeasons = nrOfSeasons;
    }
}
