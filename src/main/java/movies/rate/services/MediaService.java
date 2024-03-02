package movies.rate.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


import movies.rate.interfaces.SerializingService;
import movies.rate.model.Media;

public class MediaService implements SerializingService {

    private final String FILENAME = "media";

    // Singleton Instance
    private static MediaService INSTANCE;

    // Private constructor to prevent instatiation
    private MediaService() {
    }

    // List of media deserialized from file
    private List<Media> media;

    /**
     * @return Instance of MediaService (Singleton Pattern)
     */
    public static MediaService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MediaService();
        }

        return INSTANCE;
    }

    /**
     * Serialize all Media into file
     * Should be run on application startup
     * 
     * @throws IOException when file could not be found
     */
    @Override
    public void serialize() throws IOException {
        if (this.media != null) {
            FileOutputStream fOut = new FileOutputStream(FILENAME);
            ObjectOutputStream out = new ObjectOutputStream(fOut);
            out.writeObject(this.media);
            out.flush();
            out.close();
        }
    }

    /**
     * Deserialize all media from file
     * Should be run on application startup
     * 
     * @throws IOException            When file could not be found
     * @throws ClassNotFoundException When class of serialized object cannot be
     *                                found
     */
    @SuppressWarnings("unchecked")
    @Override
    public void deserialize() throws IOException, ClassNotFoundException {
        try {
            FileInputStream fIn = new FileInputStream(FILENAME);
            ObjectInputStream in = new ObjectInputStream(fIn);
            this.media = (List<Media>) in.readObject();
            in.close();
        } catch (FileNotFoundException e) {
            this.media = new ArrayList<>();
        }
    }

    /**
     * @return A list of all media
     */
    public List<Media> getMedia() {
        return this.media;
    }

    /**
     * Get all Media of a specific subtype
     * 
     * @param type The type of media to be returned
     * @return A list of media of the specified type
     */
    public <T extends Media> List<T> getMediaByType(Class<T> type) {
        return this.media.stream().filter(media -> type.isInstance(media)).map(media -> type.cast(media)).toList();
    }

    /**
     * Add a new piece of media
     * 
     * @param newMedia the new piece of media to be added
     * @throws IllegalArgumentException if a piece of media with the provided title
     *                                  already exists in the list
     */
    public void addMedia(Media newMedia) throws IllegalArgumentException {
        if (!this.media.stream().filter(media -> media.getTitle().equals(newMedia.getTitle())).findAny().isPresent()) {
            this.media.add(newMedia);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Replace existing media
     * @param newMedia Media to be replaced
     * @throws NoSuchElementException if no media with the given ID is present
     */
    public void updateMedia(Media newMedia) throws NoSuchElementException {
        this.media.removeIf(media -> media.getId() == newMedia.getId());
        addMedia(newMedia);
    }

    /**
     * Remove a piece of media from the list
     * 
     * @param mediaToRemove the piece of media to be removed
     */
    public void removeMedia(Media mediaToRemove) {
        this.media.removeIf(media -> media.getTitle().equals(mediaToRemove.getTitle()));
    }

    public int getNewId() {
        return this.media.stream().mapToInt(media -> media.getId()).max().orElse(0)+1;
    }

    public void resetMediaList() {
        this.media = new ArrayList<>();
    }
}
