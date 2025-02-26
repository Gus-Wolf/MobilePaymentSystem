package UserManagement.Controller;

/**
 * Controller class responsible for managing user-related operations.
 */
public class UserController {

    /**
     * Retrieves the details of a specific user.
     *
     * @param userId the ID of the user whose details need to be retrieved
     * @return a string containing user details
     */
    public String getUserDetails(int userId) {
        return "User details for ID: " + userId;
    }

    /**
     * Creates a new user with the given username.
     *
     * @param username the name of the user to be created
     * @return a confirmation message or userId
     */
    public String createUser(String username) {
        return "User created with username: " + username;
    }
}
