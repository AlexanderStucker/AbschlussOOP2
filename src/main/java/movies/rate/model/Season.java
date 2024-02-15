package movies.rate.model;

import java.io.Serializable;

public class Season implements Serializable {

    private static final long serialVersionUID = 1L;

    private int nrOfEpisodes;
    private int averageEpisodeLengthInMins;
    private double rating;


    public Season(int nrOfEpisodes, int averageEpisodeLengthInMins, int rating) {
        this.nrOfEpisodes = nrOfEpisodes;
        this.averageEpisodeLengthInMins = averageEpisodeLengthInMins;
        this.rating = rating;
    }

    public int getNrOfEpisodes() {
        return this.nrOfEpisodes;
    }

    public void setNrOfEpisodes(int nrOfEpisodes) {
        this.nrOfEpisodes = nrOfEpisodes;
    }

    public int getAverageEpisodeLengthInMins() {
        return this.averageEpisodeLengthInMins;
    }

    public void setAverageEpisodeLengthInMins(int averageEpisodeLengthInMins) {
        this.averageEpisodeLengthInMins = averageEpisodeLengthInMins;
    }

    public double getRating() {
        return this.rating;
    }

    public void setRating(double rating) throws IllegalArgumentException {
        if (rating > 5 && rating < 0) {
            throw new IllegalArgumentException("Rating is outside of the allowed Range (0-5)");
        }
        this.rating = rating;
    }


}
