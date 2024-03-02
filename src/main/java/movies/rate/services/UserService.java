package movies.rate.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import movies.rate.interfaces.SerializingService;
import movies.rate.model.User;

public class UserService implements SerializingService {

    private final String FILENAME = "users";

    // Singleton Instance
    private static UserService INSTANCE;

    // Private Constructor to prevent instatiation
    private UserService() {
    }

    // List of users deserialized from file
    private List<User> users;

    /**
     * @return Instance of UserService (Singleton Pattern)
     */
    public static UserService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserService();
        }

        return INSTANCE;
    }

    /**
     * Serialize all Users into file
     * Should be run on application shutdown
     * 
     * @throws IOException when file could not be found
     */
    @Override
    public void serialize() throws IOException {
        if (this.users != null) {

            User loginUser = LoginService.getInstance().getLoginUser();
            if(loginUser != null) {
                System.out.println("Syncing user");
                this.users.removeIf(user -> user.getUsername().equals(loginUser.getUsername()));
                this.users.add(loginUser);
            }

            FileOutputStream fOut = new FileOutputStream(FILENAME);
            ObjectOutputStream out = new ObjectOutputStream(fOut);
            out.writeObject(this.users);
            out.flush();
            out.close();
        }
    }

    /**
     * Deserialize all users from file
     * should be run on application startup
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
        this.users = (List<User>) in.readObject();
        in.close();
        } catch (FileNotFoundException e) {
            this.users = new ArrayList<>();
        }
    }

    /**
     * @return A list of all the users
     */
    public List<User> getUsers() {
        return this.users;
    }

    /**
     * Get a specific user by username
     * 
     * @param username The username of the user thats being searched
     * @return The found user
     * @throws NoSuchElementException If no user with the specified username has
     *                                been found
     */
    public User getUser(String username) throws NoSuchElementException {
        return this.users.stream().filter(user -> user.getUsername().equals(username)).findAny().orElseThrow(NoSuchElementException::new);
    }

    /**
     * Add a user to the list
     * 
     * @param newUser The user that should be added
     * @throws IllegalArgumentException if a user with the provided username already
     *                                  exists in the list
     */
    public void addUser(User newUser) throws IllegalArgumentException {
        if (!this.users.stream().filter(user -> user.getUsername().equals(newUser.getUsername())).findFirst().isPresent()) {
            this.users.add(newUser);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
