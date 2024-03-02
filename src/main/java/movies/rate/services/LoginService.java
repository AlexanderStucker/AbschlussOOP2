package movies.rate.services;

import java.util.NoSuchElementException;

import movies.rate.model.User;

public class LoginService {

    // Singleton Instance
    private static LoginService INSTANCE;

    // Private constructor to prevent instantiation
    private LoginService() {
    }

    // Currently logged in user
    private User loginUser;

    /**
     * @return Instance of LoginService (Singleton Pattern)
     */
    public static LoginService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LoginService();
        }

        return INSTANCE;
    }

    /*
     * Get the currently logged in user
     */
    public User getLoginUser() {
        return this.loginUser;
    }

    /**
     * Attempt login with credentials and set logged in user for easy access across the whole application
     * @param username Username credential
     * @param password Password credential
     * @return  The logged in user
     * @throws IllegalArgumentException If the provided password doesnt match with the found user
     * @throws NoSuchElementException   If a user with the given name does not exist
     */
    public User login(String username, String password) throws IllegalArgumentException, NoSuchElementException {
        User user = UserService.getInstance().getUser(username);

        System.out.println(user.getPassword() + " -> " + password);

        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException();
        }
        this.loginUser = user;
        return user;
    }

    public void logout() {
        this.loginUser = null;
    }
}
