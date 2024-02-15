package movies.rate.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import movies.rate.model.enums.FSKRating;
import movies.rate.model.enums.Genre;

public abstract class Media implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    private String description;
    private Date releaseDate;
    private List<Genre> genres;
    private FSKRating fskRating;

    // Actors?
    // Directors?


    public Media(String title, String description, Date releaseDate, List<Genre> genres, FSKRating fskRating) {
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.genres = genres;
        this.fskRating = fskRating;
    }

    public abstract double getRating();


    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Genre> getGenres() {
        return this.genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public FSKRating getFskRating() {
        return this.fskRating;
    }

    public void setFskRating(FSKRating fskRating) {
        this.fskRating = fskRating;
    }



}
