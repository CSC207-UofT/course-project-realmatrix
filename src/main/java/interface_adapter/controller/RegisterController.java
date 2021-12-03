package interface_adapter.controller;

import use_case.input_boundaries.UserInputBoundary;
import use_case.output_boundaries.DatabaseErrorOutputBoundary;
import use_case.output_boundaries.RegisterOutputBoundary;

/**
 * A register controller that allow user to register.
 */
public class RegisterController {
    final UserInputBoundary userIB;
    final DatabaseErrorOutputBoundary databaseErrorOutputBoundary;

    public RegisterController(UserInputBoundary userIB, DatabaseErrorOutputBoundary databaseErrorOutputBoundary) {
        this.userIB = userIB;
        this.databaseErrorOutputBoundary = databaseErrorOutputBoundary;
    }

    /**
     * User register method
     *
     * @param username user's username
     * @param password user's password
     */
    public void register(String username, String password,
                         RegisterOutputBoundary registerOB) {
        userIB.createNewUser(username, password, registerOB);
    }
}
