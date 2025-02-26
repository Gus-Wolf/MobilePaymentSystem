package UserAuthentication;

/**
 * The UserLogin class represents a simple user login mechanism
 * with functionality to store user credentials and validate login information.
 */
public class UserLogin {

    // Private fields to store username and password
    private String username;
    private String password;

    /**
     * Constructor to initialize the UserLogin with a username and a password.
     *
     * @param username the username of the user
     * @param password the password of the user
     */
    public UserLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Gets the username of the user.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password of the user.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Validates the login details by checking if the provided credentials
     * match the stored credentials.
     *
     * @param inputUsername the username to validate
     * @param inputPassword the password to validate
     * @return true if the credentials match, false otherwise
     */
    public boolean validateLogin(String inputUsername, String inputPassword) {
        return this.username.equals(inputUsername) && this.password.equals(inputPassword);
    }
}
