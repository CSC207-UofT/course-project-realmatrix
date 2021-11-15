package Controller;

import input_boundaries.UserInputBoundary;
import output_boundaries.RegisterOutputBoundary;

/**
 * A register Controller that allow user to register.
 */
public class RegisterController {
    UserInputBoundary userIB;

    public RegisterController(UserInputBoundary userIB) {
        this.userIB = userIB;
    }

    /**
     * User register method
     *
     * @param username user's username
     * @param password user's password
     */
    public void register(String username, String password, RegisterOutputBoundary registerOB) {
        userIB.createNewUser(username, password, registerOB);
    }
}
