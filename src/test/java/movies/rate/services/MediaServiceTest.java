package movies.rate.services;

import movies.rate.model.Movie;
import movies.rate.model.enums.FSKRating;
import movies.rate.model.enums.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class MediaServiceTest {

    private MediaService mediaService;

    @BeforeEach
    void setUp() {
        mediaService = MediaService.getInstance();
        mediaService.resetMediaList();
    }


    // Film hinzufügen
    @Test
    void testAddMovieSuccess() {
        Movie movie = new Movie("Test Movie", "A test movie description", new Date(),
                Arrays.asList(Genre.HORROR), FSKRating.FSK_12, 120);

        assertDoesNotThrow(() -> mediaService.addMedia(movie));
        assertTrue(mediaService.getMedia().contains(movie));
    }


    // Duplikat hinzufügen -> Sollte scheitern
    @Test
    void testAddMovieFailureDueToDuplicateTitle() {
        Movie movie1 = new Movie("Unique Movie", "First movie description", new Date(),
                Arrays.asList(Genre.HORROR), FSKRating.FSK_16, 150);
        mediaService.addMedia(movie1);

        Movie movie2 = new Movie("Unique Movie", "Second movie with duplicate title", new Date(),
                Arrays.asList(Genre.COMEDY), FSKRating.FSK_0, 90);

        assertThrows(IllegalArgumentException.class, () -> mediaService.addMedia(movie2));
    }
}
