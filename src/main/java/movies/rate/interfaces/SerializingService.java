package movies.rate.interfaces;

import java.io.IOException;

public interface SerializingService {
    public void serialize() throws IOException;
    public void deserialize() throws IOException, ClassNotFoundException;
}
